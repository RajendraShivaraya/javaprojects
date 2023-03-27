package com.joybuy.salesservice.DTO;

import com.joybuy.salesservice.entities.Enums;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.List;

@Data
@FieldNameConstants
@ToString
public class SalesOrderResponseDTO
{
    private String SalesId;
    private String custId;
    private String dlvAddress;
    private Date   transDate;
    private String receiptId;
    public Enums.SalesType salesType;
    public Enums.SalesStatus status;
    public float  salesAmount;
    public float  salesDiscount;
    public float  totalPrice;
    private float  salesTax;
    public float   amountPaid;
    private List<SalesLineDTO> salesLines;
}
