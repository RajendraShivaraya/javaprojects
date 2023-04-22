package remoteproxydesigndemoserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import remoteproxydesigndemoserver.model.MenuItems;
import remoteproxydesigndemoserver.service.RestaurantManagerImpl;

import java.util.List;

@RestController
public class ClientEntryPath
{
    @Autowired
    RestaurantManagerImpl restaurantManager;

    @GetMapping("/")
    public String home()
    {
        return "<h1>Welcome to Remote proxy design demo server- Assignment 2 CECS 575 - Obj Oriented Analysis & Design </h1>";
    }

    @GetMapping("/menu/{id}")
    public List<MenuItems> getMenu(@PathVariable int id)
    {
        return restaurantManager.getMenu(id);
    }

    @GetMapping("/address/{id}")
    public String getAddress(@PathVariable int id)
    {
        return restaurantManager.getAddress(id);
    }

    @GetMapping("/contact/{id}")
    public Long getContactNum(@PathVariable int id)
    {
        return restaurantManager.getContactNumber(id);
    }
}
