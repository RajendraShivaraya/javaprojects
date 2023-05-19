package securityoauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPoints
{
    @GetMapping("/Home")
    public String Home()
    {
        return "<h1>Home page</h1>";
    }

    @GetMapping("/Public")
    public String PublicAccess()
    {
        return "<h1>Public page</h1>";
    }

    @GetMapping("/Secured")
    public String SecuredAccess()
    {
        return "<h1>Secured page</h1>";
    }

}
