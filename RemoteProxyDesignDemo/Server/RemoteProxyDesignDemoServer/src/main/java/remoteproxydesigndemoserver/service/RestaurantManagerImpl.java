package remoteproxydesigndemoserver.service;

import org.springframework.stereotype.Service;
import remoteproxydesigndemoserver.model.MenuItems;

import java.util.List;

@Service
public class RestaurantManagerImpl
{
    LoadRestaurantData loadRestaurantData;

    public List<MenuItems> getMenu(int restaurantNumber)
    {
        loadRestaurantData = new LoadRestaurantData(restaurantNumber);
        return loadRestaurantData.menu;
    }

    public String getAddress(int restaurantNumber)
    {
        loadRestaurantData = new LoadRestaurantData(restaurantNumber);
        return loadRestaurantData.address;
    }

    public Long getContactNumber(int restaurantNumber)
    {
        loadRestaurantData = new LoadRestaurantData(restaurantNumber);
        return loadRestaurantData.contactNum;
    }
}
