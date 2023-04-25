package Package;

public class Main
{
    public static void main(String[] args)
    {
        DiscountNotification discNotify = new DiscountNotification();
        DiscountObserver cust1 = new Customer("Rajendra", "Rajendra@test.com", 9844444584L);
        discNotify.registerCustomer(cust1);
        DiscountObserver cust2 = new PremiumCustomers("John", "John@gmail.com", 123564332L);
        discNotify.registerCustomer(cust2);
        DiscountObserver cust3 = new Customer("Paul", "Paul@hotmail.com", 1223334444L);
        discNotify.registerCustomer(cust3);
        DiscountObserver cust4 = new PremiumCustomers("James", "James@yahoo.com", 4252023123L);
        discNotify.registerCustomer(cust4);

        System.out.println("***********************Wendy Notification**************************************");
        discNotify.registerRestaurantForDiscount("Wendy's", 10);
        discNotify.unregisterCustomer(cust1);
        discNotify.unregisterCustomer(cust3);
        System.out.println("************************KFC Notification**************************************");
        discNotify.registerRestaurantForDiscount("KFC", 8);
    }
}