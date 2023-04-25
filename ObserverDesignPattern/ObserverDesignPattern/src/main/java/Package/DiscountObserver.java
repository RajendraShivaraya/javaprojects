package Package;

import java.util.Map;

public interface DiscountObserver
{
    public void notifyNewDiscount(Map<String, Integer> restaurantDiscount);
}
