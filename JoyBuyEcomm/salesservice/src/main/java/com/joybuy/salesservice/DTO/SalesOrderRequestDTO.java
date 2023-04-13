package com.joybuy.salesservice.DTO;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import java.util.List;

@Data
@FieldNameConstants
@ToString
public class SalesOrderRequestDTO
{
    private String      custId;
    private List<ItemsDTO> items;
}
