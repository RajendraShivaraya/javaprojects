package kafkademo.model;

public class JoybuyEnums
{
    public enum PaymentMethods
    {
        CARD,
        UPI,
        WALLET,
        NETBanking,
        RewardPoints
    }

    public enum OrderStatus
    {
        CREATED,
        IN_PROGRESS,
        OUT_FOR_SHIPPING,
        IN_TRANSIT,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CLOSED,
        CANCELLED,
        ON_HOLD
    }

    public enum apiResponse
    {
        SUCCESS,
        ERROR
    }
}
