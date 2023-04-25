package templatemethoddesign;

public class CODPayment extends PaymentManager
{

    @Override
    void CollectPayDetails()
    {
        System.out.println("Please provide exact cash for payment");
    }

    @Override
    void InitializePayment()
    {
        System.out.println("Cash collection in progress");
    }

    @Override
    void UpdateTransaction()
    {
        System.out.println("Order has updated with payment method CASH");
    }

    @Override
    void OrderInvoiceAndConfirmation()
    {
        System.out.println("Order has been Invoiced, enjoy the Food");
    }
}
