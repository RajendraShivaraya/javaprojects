package com.joybuy.paymentservice.DTO;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class SalesLineDTO
{
    private int    lineNum;
    private String itemId;
    private int    qty;
    public float   salesPrice;
    public float   lineAmount;
    public float   lineDisc;
    public float   totalPrice;
}
