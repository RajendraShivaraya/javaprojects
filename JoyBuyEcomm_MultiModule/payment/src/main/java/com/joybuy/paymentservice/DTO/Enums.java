package com.joybuy.paymentservice.DTO;

public class Enums
{
    public enum SalesType{Ecomm, InStore}
    public enum SalesStatus{Created, PartiallyInvoice, Invoiced}
    public enum PaymentStatus{Pending, Failed, Authorized, Voided, Posted, Settled, Refund, Refunded}
}
