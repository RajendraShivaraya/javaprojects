package com.joybuy.salesservice.DTO;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.List;

@Data
@FieldNameConstants
public class SalesOrderResponseDTO
{
    private String SalesId;
    private String custId;
    private float  salesAmount;
    private float  salesTax;
    private String dlvAddress;
    private Date   transDate;
    private String receiptId;
    private List<SalesLineDTO> salesLines;

}
