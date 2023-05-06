package security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController
{
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
}
