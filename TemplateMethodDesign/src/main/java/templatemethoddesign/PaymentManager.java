package templatemethoddesign;
import templatemethoddesign.EnumsConfiguration;

import java.awt.desktop.ScreenSleepEvent;

public abstract class PaymentManager
{


    abstract void CollectPayDetails();
    abstract void InitializePayment();
    abstract void UpdateTransaction();
    abstract void OrderInvoiceAndConfirmation();

    public final void ProcessPayment()
    {
        System.out.println("Payment processing - Begin");
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        CollectPayDetails();
        InitializePayment();
        UpdateTransaction();
        OrderInvoiceAndConfirmation();
        System.out.println();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Payment processing - End");
    }
}
