package remoteproxydesigndemoserver.service;

import org.springframework.stereotype.Service;
import remoteproxydesigndemoserver.model.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class LoadRestaurantData
{

    public List<MenuItems> menu;
    public String          address;
    public Long            contactNum;
    public String          restaurantId;

    public LoadRestaurantData(int restaurantNum)
    {
        if (restaurantNum == 1)
            initRestaurant1();
        else
            initRestaurant2();
    }

    public void initRestaurant1()
    {
        this.restaurantId = "Rest001";
        List<MenuItems> menuItemsList = new ArrayList<>();
        menuItemsList.add(new MenuItems("CHN001", "Chicken Wings", "Chicken wings with barbeque sauce & mild spice", 10D));
        menuItemsList.add(new MenuItems("CHN002", "Chicken burger", "Chicken burger with wheat bread", 12D));
        menuItemsList.add(new MenuItems("CHN003", "Chicken curry", "Chicken curry with asian spices", 14D));
        menuItemsList.add(new MenuItems("CHN004", "Chicken fried rice", "Chicken fried rice with noodles mix", 15D));

        this.menu = menuItemsList;
        this.address = "Bellflower Blvd, Long Beach, CA, 90815";
        this.contactNum = 5621234567L;
    }

    public void initRestaurant2()
    {
        this.restaurantId = "Rest002";
        List<MenuItems> menuItemsList = new ArrayList<>();
        menuItemsList.add(new MenuItems("VEG001", "Veg chat", "Vegetable chats", 10D));
        menuItemsList.add(new MenuItems("VEG002", "Veg burger", "Vegetable burger", 10D));
        menuItemsList.add(new MenuItems("VEG003", "Veg curry", "Vegetable curry", 10D));
        menuItemsList.add(new MenuItems("VEG004", "Veg fried rice", "Vegetable fried rice", 10D));

        this.menu = menuItemsList;
        this.address = "Long Beach Blvd, Los Angeles, CA, 90456";
        this.contactNum = 5620001234L;
    }
}
