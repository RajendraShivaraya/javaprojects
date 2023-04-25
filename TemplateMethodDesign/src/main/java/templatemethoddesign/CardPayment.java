package templatemethoddesign;

import java.util.Scanner;

public class CardPayment extends PaymentManager
{
    EnumsConfiguration.CARD    selectedCard;
    Long cardNumber;

    @Override
    void CollectPayDetails()
    {
        System.out.println("Select card type");
        System.out.println("Enter 1 - CREDIT");
        System.out.println("Enter 2 - DEBIT");

        Scanner scanner = new Scanner(System.in);
        Integer cardSelection = scanner.nextInt();
        switch(cardSelection)
        {
            case 1:
                selectedCard = EnumsConfiguration.CARD.CREDIT;
                System.out.println("Enter Credit Card Number");
                cardNumber = scanner.nextLong();
                break;
            case 2:
                selectedCard = EnumsConfiguration.CARD.DEBIT;
                System.out.println("Enter Debit Card Number");
                cardNumber = scanner.nextLong();
                break;
            default:
                System.out.println("OOPS - Incorrect card selection");
        }
    }

    @Override
    void InitializePayment()
    {
        System.out.println("Amount is charged to " + selectedCard + " card " + cardNumber);
    }

    @Override
    void UpdateTransaction()
    {
        System.out.println("Order has updated with payment method " + selectedCard + " card & " + cardNumber);
    }

    @Override
    void OrderInvoiceAndConfirmation()
    {
        System.out.println("Order has been Invoiced, enjoy the Food");
    }
}
