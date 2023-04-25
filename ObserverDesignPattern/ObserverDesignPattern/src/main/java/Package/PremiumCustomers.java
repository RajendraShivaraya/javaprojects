package Package;

import java.util.Map;

public class PremiumCustomers implements DiscountObserver
{
    public String name;
    public String email;
    public long   phone;

    public PremiumCustomers(String name, String email, long phone)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public void notifyNewDiscount(Map<String, Integer> restaurantDisc)
    {
        System.out.println("------------Premium Customer------------------");
        System.out.println("Sent email notification to " + name + " at " + email);
        System.out.println("Sent SMS notification to " + name + " at " + phone);
        for (String key : restaurantDisc.keySet() )
        {
            System.out.println("New discount from " + key + " flat " + restaurantDisc.get(key) + "% off and an additional 5% discount from Company");
        }
    }
}
