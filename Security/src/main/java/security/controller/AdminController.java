package security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.datamodel.Roles;
import security.datamodel.Users;
import security.repository.RolesRepository;
import security.service.UserService;

@RestController
public class AdminController
{
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    UserService userService;

    @GetMapping("/admin/")
    public String adminHome()
    {
        return "<h1>Welcome Admin Page</h1>";
    }

    @GetMapping("/admin/console")
    public String adminConsoleHome()
    {
        return "<h1>Admin console Page</h1>";
    }

    @PostMapping("/admin/createrole/{roleName}")
    public String createRole(@PathVariable String roleName)
    {
        try
        {
            Roles newRole = new Roles();
            newRole.setName(roleName);
            rolesRepository.save(newRole);
            return "Role " + roleName + "created successfully";
        }
        catch (Exception ex)
        {
            return "Failed to create role" + ex.getMessage();
        }
    }
}
