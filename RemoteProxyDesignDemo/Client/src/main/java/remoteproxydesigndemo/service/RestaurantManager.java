package remoteproxydesigndemo.service;

import remoteproxydesigndemo.model.MenuItems;

import java.util.List;

public interface RestaurantManager
{
    public List<MenuItems> getMenu(int restaurantNumber);
    public String          getAddress(int restaurantNumber);
    public Long            getContactNumber(int restaurantNumber);
}
