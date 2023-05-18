package securityrememberme.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpRequest;

import static org.springframework.web.util.WebUtils.getCookie;

@RestController
public class EndPoints
{
    @GetMapping("/")
    public String home()
    {
        return "<h1>Authentication & remember me test </h1>";
    }

    @GetMapping("/build")
    public String buildURL()
    {
         final String url= UriComponentsBuilder.fromHttpUrl("http://localhost:8080/")
                 .path("/register/verify")
                 .queryParam("logintoken", "hjdsa973hr4jhehjsdj")
                 .queryParam("jobid", "ERPMSC001")
                 .queryParam("ApplicationId", 20230515001L)
                 .toUriString();

        return url;
    }

    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String logintoken,
                                 @RequestParam String jobid,
                                 @RequestParam Long ApplicationId,
                                 final Model model, RedirectAttributes redirAttr)
    {
        if(logintoken.isEmpty())
        {
            return "user.registration.verification.missing.token";
        }
        else
        {
            return "user.registration.verification.success";
        }
    }

    @GetMapping("/movie/{moviename}")
    public String movieSearch(@PathVariable String moviename)
    {
        if(moviename.isEmpty())
        {
            return "Movie name cannot be empty";
        }

        try
        {
            final String urlString = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/")
                    .path("/customsearch/v1")
                    .queryParam("key", "312213") // Key to be generated from google account
                    .queryParam("cx", "017576662512468239146:omuauf_lfve")
                    .queryParam("q", moviename)
                    .toUriString();


            // Create URL object with the endpoint you want to call
            URL url = new URL(urlString );

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method (GET, POST, PUT, DELETE, etc.)
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response body
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                responseBody.append(line);
            }
            reader.close();

            // Print the response body
            System.out.println("Response Body: " + responseBody.toString());
            String returnMsg = responseBody.toString();

            // Close the connection
            connection.disconnect();

            return  returnMsg;
            }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response)
    {
        // Create a new cookie
        Cookie cookie = new Cookie("RajendraCookie", "TestofcookiesItshouldIncludeSpace");

        // Set additional properties for the cookie
        cookie.setMaxAge(3600); // Set the maximum age of the cookie in seconds (1 hour in this example)
        cookie.setPath("/"); // Set the path for which the cookie is valid ("/" means the entire application)
        cookie.setSecure(true); // Set whether the cookie should only be transmitted over secure (HTTPS) connections

        // Add the cookie to the response
        response.addCookie(cookie);

        return "Cookie has been set.";
    }

    @GetMapping("/read-cookie")
    public String userProfile(HttpServletRequest request)
    {
        // Retrieve the viewedArticles cookie
        Cookie cookieObject = getCookie(request, "RajendraCookie");
        if (cookieObject != null)
        {
            String viewedArticleIds = cookieObject.getValue();
            return viewedArticleIds;
        }
        return "Unable to read cookie";
    }

    @GetMapping("/set-cookie/page1")
    public String setCookieForInnerEndPoint(HttpServletResponse response)
    {
        // Create a new cookie
        Cookie cookie = new Cookie("RajendraCookie_InnerPage", "TestofcookiesItshouldIncludeSpace");

        // Set additional properties for the cookie
        cookie.setMaxAge(3600); // Set the maximum age of the cookie in seconds (1 hour in this example)
        cookie.setPath("/read-cookie"); // Set the path for which the cookie is valid ("/" means the entire application)
        cookie.setSecure(true); // Set whether the cookie should only be transmitted over secure (HTTPS) connections

        // Add the cookie to the response
        response.addCookie(cookie);

        return "Cookie has been set.";
    }
}
