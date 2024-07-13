Steps to works with this project

Given
0. access swagger page using http://localhost:8082/swagger-ui/index.html
1. admin/admin, user as default with admin privilege

2. we can create any user using following syntax
{
    "username": "test",
    "password": "test",
    "enabled": true,
    "businessUserAuthorities": [
        {
            "username": "test",
            "authority": "admin" // this authority decides which user can add/update/delete user from database
                                    only user with admin/manager authority is having this rights
                                    other authorities can be anything,
                                    we can supply as many number of authority as we want
        }
    ],
    "encrAlgo": "bcrypt" // this is pwd algorithm we want, we are only supporting 2 types as of now bcrypt/scrypt
                        // to work with scrypt, we need to add bouncy castle library
}

3. if you try creating user, don't forget to pass user/password as basic authentication mode
4. as we implemented JdbcUserDetailsManager, our database tables needs to be of with name users/authority
5. for users table, username, password, enabled these fields are mandatory
6. for authority username, authority these fields are mandatory
6.2 we have also added Authentication instance from SecurityContextHolder to retrieve authenticated user details
7. this project can be extended to support Liquibase / Global exception handling / AOP

