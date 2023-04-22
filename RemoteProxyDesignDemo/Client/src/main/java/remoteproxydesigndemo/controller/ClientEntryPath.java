package remoteproxydesigndemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import remoteproxydesigndemo.model.MenuItems;
import remoteproxydesigndemo.service.MenuService;

import java.util.List;

@RestController
public class ClientEntryPath
{
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public String home()
    {
        return "<h1>Welcome to Remote proxy design demo Client - Assignment 2 CECS 575 - Obj Oriented Analysis & Design </h1>";
    }

    @GetMapping("/menu/{id}")
    public List<MenuItems> getMenu(@PathVariable int id)
    {
        return menuService.getRestaurantMenu(id);
    }

    @GetMapping("/address/{id}")
    public String getAddress(@PathVariable int id)
    {
        return menuService.getRestaurantAddress(id);
    }

    @GetMapping("/contact/{id}")
    public Long getContactNum(@PathVariable int id)
    {
        return menuService.getRestaurantContactNumber(id);
    }
}
