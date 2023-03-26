package com.joybuy.salesservice.DTO;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import java.util.List;

@Data
@FieldNameConstants
public class SalesOrderRequestDTO
{
    private String      custId;
    private List<ItemsDTO> items;
}
