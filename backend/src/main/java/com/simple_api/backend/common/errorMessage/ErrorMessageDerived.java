package com.simple_api.backend.common.errorMessage;

import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

public class ErrorMessageDerived {
    
    public String notFoundError( Cat cat ){
        StringBuilder stb = new StringBuilder();
        stb.append(cat.getName());
        stb.append(" does not exists with given id of ");
        stb.append(cat.getId());
        return stb.toString();
    }

    public String notFoundError( CatNative catNative ){
        StringBuilder stb = new StringBuilder();
        stb.append(catNative.getName());
        stb.append(" does not exists with given id of ");
        stb.append(catNative.getId());
        return stb.toString();
    }

    public String notFoundErrorById( Long id ){
        StringBuilder stb = new StringBuilder();
        stb.append("The cat does not exists with given id of ");
        stb.append(id);
        return stb.toString();
    }

    public String conflictError(){
        return "Cat name is in use!";
    }
    
}
