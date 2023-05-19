# Create End points for public & restricted access
Create a controller class with end point having public & secured access.

# Create Security Filter chain to configure the security
Add OAuthLogin Along with standard form login.

# Add OAuth client details in Application.Properties

spring.security.oauth2.client.registration.github.client-id=client_id_from_github
spring.security.oauth2.client.registration.github.client-secret=client_secret_from_github

# GitHub App registration
Register app in GitHub for OAuth

1. Go to GitHub -> Settings -> Developer Settings
2. Create a new App to test OAuth
3. Set Homepage URL = "http://localhost:8080/"
4. Set callback URL ="http://localhost:8080/login/oauth2/code/github"
5. Note ClientId & Create a client secret

# Run the APP
Use OAuth login to application.