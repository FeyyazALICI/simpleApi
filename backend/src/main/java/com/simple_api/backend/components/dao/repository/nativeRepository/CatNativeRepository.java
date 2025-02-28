package com.simple_api.backend.components.dao.repository.nativeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatNativeMapper;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

import jakarta.transaction.Transactional;

@Repository
public class CatNativeRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public CatNativeRepository(
        JdbcTemplate jdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<CatNative> getAllCats(){
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT                         \n");
        stb.append("t.id,                          \n");
        stb.append("t.name,                        \n");
        stb.append("t.weight                       \n");
        stb.append("FROM                           \n");
        stb.append("cat t                          \n");
        List<CatNative> resultList = jdbcTemplate.query(stb.toString(), new CatNativeMapper());
        
        return resultList;
    }

    public CatNative getCatById(Long id) {
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT                         \n");
        stb.append("t.id,                          \n");
        stb.append("t.name,                        \n");
        stb.append("t.weight                       \n");
        stb.append("FROM                           \n");
        stb.append("cat t                          \n");
        stb.append("WHERE                          \n");
        stb.append("t.id = ?                       \n");
    
        List<CatNative> resultList = jdbcTemplate.query(
            stb.toString(),
            ps -> ps.setLong(1, id), // Set the `id` parameter using a PreparedStatementSetter
            new CatNativeMapper()    // Use your row mapper to map the result set
        );
    
        // Return the first result, or null if no result is found
        return resultList.isEmpty() ? null : resultList.get(0);
    }


    @Transactional
    public boolean insertCat(CatNative catNative) throws Exception{
        if(   ifCatExistsByName(catNative)   ){
            return false;
        }else{
            StringBuilder stb = new StringBuilder();
            stb.append("INSERT INTO                         \n");
            stb.append("cat                                 \n");
            stb.append("(name, weight)                      \n");
            stb.append("VALUES (?, ?)                       \n");
            
            // Use jdbcTemplate.update with a PreparedStatementSetter
            int result = jdbcTemplate.update(stb.toString(), 
                ps -> {
                    ps.setString(1, catNative.getName());
                    ps.setBigDecimal(2, catNative.getWeight());
                });
            
            if(    result>0   )
                return true;
    
            return false;
        }
    }

    @Transactional
    public boolean updateCat(CatNative catNative) throws Exception {
        // Check if the cat exists by id
        if (!ifCatExistsById(catNative.getId())) {
            return false; // If cat doesn't exist, return false
        } else {
            StringBuilder stb = new StringBuilder();
            stb.append("UPDATE                          \n");
            stb.append("cat t                           \n");

            stb.append("SET                             \n");
            stb.append("t.weight = ?  ,                 \n");
            stb.append("t.name = ?                      \n");

            stb.append("WHERE                           \n");
            stb.append("t.id = ?                        \n");
            
            // Use jdbcTemplate.update with a PreparedStatementSetter
            int result = jdbcTemplate.update(stb.toString(),
                ps -> {
                    ps.setBigDecimal(1, catNative.getWeight()); 
                    ps.setString(2, catNative.getName());   
                    ps.setLong(3, catNative.getId());       
                });
            
            return result > 0; // Return true if the update was successful
        }
    }


    @Transactional
    public boolean deleteCat(Long id) throws SQLException {
        // Check if the cat exists by id
        if (!ifCatExistsById(id)) {
            throw new SQLException("Cat with id of " + id + " not found!");
        } else {
            StringBuilder stb = new StringBuilder();
            stb.append("DELETE FROM                          \n");
            stb.append("cat t                           \n");

            stb.append("WHERE                           \n");
            stb.append("t.id = ?                        \n");
            
            // Use jdbcTemplate.update with a PreparedStatementSetter
            int result = jdbcTemplate.update(stb.toString(),
                ps -> {  
                    ps.setLong(1, id);       
                });
            
            return result > 0; // Return true if the update was successful
        }
    }



    // Auxilary methods ----------------------------------------------------------------------------------------
    public boolean ifCatExistsByName(CatNative catNative) {
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT          \n");
        stb.append("COUNT(t.id)     \n");
        stb.append("FROM            \n");
        stb.append("cat t           \n");
        stb.append("WHERE           \n");
        stb.append("t.name = ?      \n");

        // Use jdbcTemplate.query with a PreparedStatementSetter and a ResultSetExtractor
        int result = jdbcTemplate.query(stb.toString(), 
            ps -> ps.setString(1, catNative.getName()), 
            (ResultSet rs) -> {
                return rs.next() ? rs.getInt(1) : 0; // Return 0 if no rows are returned
            }
        );
        return (result>0);
    }

    public boolean ifCatExistsById(Long id) {
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT          \n");
        stb.append("COUNT(t.id)     \n");
        stb.append("FROM            \n");
        stb.append("cat t           \n");
        stb.append("WHERE           \n");
        stb.append("t.id = ?        \n");

        // Use jdbcTemplate.query with a PreparedStatementSetter and a ResultSetExtractor
        int result = jdbcTemplate.query(stb.toString(), 
            ps -> ps.setLong(1, id), 
            (ResultSet rs) -> {
                return rs.next() ? rs.getInt(1) : 0; // Return 0 if no rows are returned
            }
        );
        return (result>0);
    }
    // Auxilary methods ----------------------------------------------------------------------------------------




}