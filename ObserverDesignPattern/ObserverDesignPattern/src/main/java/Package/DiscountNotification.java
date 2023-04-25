package Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountNotification implements DiscountManager
{
    private List<DiscountObserver> customers;
    private Map<String, Integer> restaurantDisc;

    public DiscountNotification()
    {
        this.restaurantDisc = new HashMap<String, Integer>();
        this.customers = new ArrayList<>();
    }

    public void registerRestaurantForDiscount(String restaurantName, Integer discountPercentage)
    {
        restaurantDisc.put(restaurantName, discountPercentage);
        notifyCustomers();
    }

    @Override
    public void registerCustomer(DiscountObserver customer)
    {
        customers.add(customer);
    }

    @Override
    public void unregisterCustomer(DiscountObserver customer)
    {
        customers.remove(customer);
    }

    @Override
    public void notifyCustomers()
    {
        for (DiscountObserver customer : customers)
        {
            customer.notifyNewDiscount(restaurantDisc);
        }
    }
}
