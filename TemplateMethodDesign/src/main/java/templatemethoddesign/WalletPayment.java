package templatemethoddesign;

import java.util.Scanner;

public class WalletPayment extends PaymentManager
{
    EnumsConfiguration.Wallets selectedWallet;

    @Override
    void CollectPayDetails()
    {
        System.out.println("Provide Wallet for payment");
        System.out.println("Enter 1 - Apple Pay");
        System.out.println("Enter 2 - Google pay");
        System.out.println("Enter 3 - PayTM Pay");
        System.out.println("Enter 4 - PhonePe Pay");

        Scanner scanner = new Scanner(System.in);
        Integer walletSelection = scanner.nextInt();
        switch(walletSelection)
        {
            case 1:
                selectedWallet = EnumsConfiguration.Wallets.APPLEPAY;
                break;
            case 2:
                selectedWallet = EnumsConfiguration.Wallets.GPAY;
                break;
            case 3:
                selectedWallet = EnumsConfiguration.Wallets.PAYTM;
                break;
            case 4:
                selectedWallet = EnumsConfiguration.Wallets.PHONEPAY;
                break;
            default:
                System.out.println("OOPS - Incorrect wallet selection");
        }
    }

    @Override
    void InitializePayment()
    {
        System.out.println(selectedWallet + " Payment is progress");
    }

    @Override
    void UpdateTransaction()
    {
        System.out.println("Order has updated with payment method WALLET & provider " + selectedWallet);
    }

    @Override
    void OrderInvoiceAndConfirmation()
    {
        System.out.println("Order has been Invoiced, enjoy the Food");
    }
}
