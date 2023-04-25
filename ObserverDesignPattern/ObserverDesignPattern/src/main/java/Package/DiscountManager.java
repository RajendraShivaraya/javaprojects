package Package;

public interface DiscountManager
{
    void registerCustomer(DiscountObserver observer);
    void unregisterCustomer(DiscountObserver observer);
    void notifyCustomers();

}
