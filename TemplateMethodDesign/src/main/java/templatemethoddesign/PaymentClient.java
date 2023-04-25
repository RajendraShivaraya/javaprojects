package templatemethoddesign;

import java.io.IOException;
import java.util.Scanner;

public class PaymentClient extends NetBankingPayment {
    public  void processPayment() throws IOException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select one of Payment Methods available as below ");
        System.out.println("Enter 1 - CASH On Delivery");
        System.out.println("Enter 2 - CARD Payment");
        System.out.println("Enter 3 - WALLET Payment");
        System.out.println("Enter 4 - NETBANKING Payment");

        Integer paymSelection = scanner.nextInt();
        switch(paymSelection)
        {
            case 1:
                PaymentManager cod = new CODPayment();
                cod.ProcessPayment();
                break;
            case 2:
                PaymentManager card = new CardPayment();
                card.ProcessPayment();
                break;
            case 3:
                PaymentManager wallet = new WalletPayment();
                wallet.ProcessPayment();
                break;
            case 4:
                PaymentManager netBank = new NetBankingPayment();
                netBank.ProcessPayment();
                break;
            default:
                System.out.println("OOPS - Incorrect selection");
        }

    }
    public static void main(String[] args) throws IOException
    {
        PaymentClient paymentClient = new PaymentClient();
        paymentClient.processPayment();
    }
}
