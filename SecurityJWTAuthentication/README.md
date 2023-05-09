# JWT Generation

1. Create end point which will return the JWT. In my case I have created "/authenticate/" end point.
2. Above end point should receive the username and password to validate the requester who is requesting for JWT.
3. JWTRequest class is create which has user & name password field and placed as input parameter for "/authenticate/"
4. Create a filter-chain which would just permit endpoint "/authenticate/" but all other end point would be authenticated using JWT.

5. Post http://localhost:8080/authenticate along with username & password in body
6. this will validate the user info 
7. set the SecurityContextHold with user info
8. returns the JWT (JWTResponse)

# JWT Authentication

1. Use the JWT received in previous section
2. Use it get/post of any other end points. User token in Authorization along with <Bearer>_<Token>
3. This should validate the JWT and return results.
