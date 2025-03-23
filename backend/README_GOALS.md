# GOALS
    - Creating a backend project with appropriate structure
    - Using both JPA and JDBC
    - Using entity and pojo classes when reaching database
    - Using DTO classes for end point communication
    - Implementing interfaces in controllers and services
    - Able to do CRUD operation
    - Able to send the data in Excel file
    - Implementing swagger
    - Having test and production databases & having corresponding .properties files (able to switch between test & prod)


# CRUD
C (Create) → POST
R (Read)   → GET
U (Update) → PUT
D (Delete) → DELETE


/* !!! no need to add @Autowired above the constructors because !!!
    - function: The @Autowired annotation tells Spring to automatically inject an instance of 
        CatRepoJpa (your repository interface) when creating an instance of MainServiceJpa.
    - is it required: No, it's optional in this case. Since Spring Boot 2.6+, 
        if a class has only one constructor, Spring automatically injects dependencies without needing @Autowired.
*/