package templatemethoddesign;

import java.util.Scanner;

public class NetBankingPayment extends PaymentManager
{
    Long accNumber;
    EnumsConfiguration.BANK selectedBank;

    @Override
    void CollectPayDetails()
    {
        System.out.println("Select the bank");
        System.out.println("Enter 1 - USBank");
        System.out.println("Enter 2 - CITI");
        System.out.println("Enter 3 - JPMorgan");
        System.out.println("Enter 4 - WellsFargo");

        Scanner scanner = new Scanner(System.in);
        Integer cardSelection = scanner.nextInt();
        switch(cardSelection)
        {
            case 1:
                selectedBank = EnumsConfiguration.BANK.USBank;
                System.out.println("Enter Account Number");
                accNumber = scanner.nextLong();
                break;
            case 2:
                selectedBank = EnumsConfiguration.BANK.CITI;
                System.out.println("Enter Account Number");
                accNumber = scanner.nextLong();
                break;
            case 3:
                selectedBank = EnumsConfiguration.BANK.JPMorgan;
                System.out.println("Enter Account Number");
                accNumber = scanner.nextLong();
                break;
            case 4:
                selectedBank = EnumsConfiguration.BANK.WellsFargo;
                System.out.println("Enter Account Number");
                accNumber = scanner.nextLong();
                break;
            default:
                System.out.println("OOPS - Incorrect bank selection");
        }
    }

    @Override
    void InitializePayment()
    {
        System.out.println("Amount is charged to " + selectedBank + " & account number " + accNumber);
    }

    @Override
    void UpdateTransaction() {
        System.out.println("Order has updated with payment method Bank & account number " + accNumber);
    }

    @Override
    void OrderInvoiceAndConfirmation()
    {
        System.out.println("Order has been Invoiced, enjoy the Food");
    }
}
