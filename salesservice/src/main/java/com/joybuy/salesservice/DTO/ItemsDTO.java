package com.joybuy.salesservice.DTO;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class ItemsDTO
{
    public String itemId;
    public int    qty;
    public float  lineDisc;
    public String inventColorId;
    public String inventSizeId;
}
