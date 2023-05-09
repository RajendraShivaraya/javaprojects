package securityjwtauthentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController
{
    @RequestMapping({ "/greeting" })
    public String welcomePage()
    {
        return "Welcome!";
    }
}
