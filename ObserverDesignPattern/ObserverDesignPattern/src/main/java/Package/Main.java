package Package;

public class Main
{
    public static void main(String[] args)
    {
        Customer cust1 = new Customer("Rajendra", "Rajendra@test.com", 9844444584L);
        Customer cust2 = new Customer("John", "John@gmail.com", 123564332L);
        Customer cust3 = new Customer("Paul", "Paul@hotmail.com", 1223334444L);

        DiscountNotification discNotify = new DiscountNotification();
        discNotify.registerCustomer(cust1);
        discNotify.registerCustomer(cust2);
        discNotify.registerCustomer(cust3);
        discNotify.registerRestaurantForDiscount("Wendy's", 10);
        discNotify.registerRestaurantForDiscount("In&Out", 7);
        discNotify.registerRestaurantForDiscount("CaliforniaPizzaKitchen", 5);
    }
}