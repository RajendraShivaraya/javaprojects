package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public boolean createUser(@RequestBody Users user)
    {
        return userService.createUser(user);
    }
}
