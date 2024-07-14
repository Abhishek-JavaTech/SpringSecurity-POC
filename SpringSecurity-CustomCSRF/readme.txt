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

2.2 here while adding this, you will get 403 Forbidden error and to test for CSRF we need to use another api which is
    http://localhost:8082/users/test



