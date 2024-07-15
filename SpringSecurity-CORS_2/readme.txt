This project is set to work for CORS origin check

0. we have enabled admin/admin as default username and password
Steps
1. from this (SpringSecurityCors2Application) class comment out line @CrossOrigin("http://localhost") and access url http://localhost:8080/index
you will get 200 response, dont forget to pass basic authentication

2. @CrossOrigin, add this in place of commented line from #1 and access the same URL, you will get 200 response, if you observer response header you will get
Key as "Access-Control-Allow-Origin" and value as "*", which mean even after adding @CrossOrigin Spring is allowing for all origins

3. @CrossOrigin("http://localhost"), now add this line in place of commented line from #1, and access url again but this time,
also send one header called, origin -> http://localhost
and check response you will get 200

now replace the value of same header called, origin -> http://example.com
and check response you will get 403, and that means we are trying to restrict application access to http://localhost, this origin only other origin will not be
able to use application