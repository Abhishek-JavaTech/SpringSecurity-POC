this project is implementation of PreAuthorize / PostAuthorize / PreFilter / PostFilter / Secured / RolesAllowed annotation

0. given we created few users reader/admin/writer with specfic roles and authorities,
to test for Pre/Post Authorize annotation then use user1/ user2/ user3 from SecurityConfig class
to test for Secured/ RolesAllowed / RolesAllowed/ Secured annotation then use user4/ user5/ user6 from SecurityConfig class

1. PreAuthorize - annotation test before executing method, whereas PostAuthorize method tests after executing method (this can be identified from log added in method)

2. Secured / RolesAllowed annotation works as PreAuthorize Annotation and works with Roles instead of authorities

4. PreFilter, filters incoming collection with condition mentioned in annotation attribute

5. PostFilter, it works on collection returning from method on which this annotation used

6. this annotations works only after we use
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) on security config class