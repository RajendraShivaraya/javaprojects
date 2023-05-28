package securityjwtauthentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PublicController
{
    @RequestMapping(value = "/greeting" , method = RequestMethod.GET)
    public String welcomePage()
    {
        return "Welcome!";
    }
}
