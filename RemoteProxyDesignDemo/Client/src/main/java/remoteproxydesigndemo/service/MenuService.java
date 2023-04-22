package remoteproxydesigndemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remoteproxydesigndemo.model.MenuItems;

import java.util.List;

@Service
public class MenuService
{
    @Autowired
    RestaurantManagerImplProxy proxy;

    public List<MenuItems> getRestaurantMenu(int restNumber)
    {
        return proxy.getMenu(restNumber);
    }

    public String getRestaurantAddress(int restNumber)
    {
        return proxy.getAddress(restNumber);
    }

    public Long getRestaurantContactNumber(int restNumber)
    {
        return proxy.getContactNumber(restNumber);
    }
}
