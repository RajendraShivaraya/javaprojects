package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.datamodel.Users;
import security.service.UserService;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/user/")
    public List<Users> getUsers()
    {
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable String id)
    {
        return userService.getUserById(id);
    }

}
