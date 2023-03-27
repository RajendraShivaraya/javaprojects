package com.joybuy.paymentservice.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SalesOrderDTO
{
    private String SalesId;
    private String custId;
    private String receiptId;
    public float   salesAmount;
}
