package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.datamodel.Users;
import security.service.UserService;

@RestController
public class FreeAccessController
{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHome()
    {
        return "<h1>Welcome home</h1>";
    }

    @PostMapping("/signup/")
    public boolean createUser(@RequestBody Users user)
    {
        return userService.createUser(user);
    }
}
