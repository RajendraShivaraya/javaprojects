package com.joybuy.inventoryservice.DTO;

import com.joybuy.inventoryservice.entities.InventDim;
import lombok.Data;

import java.util.List;

@Data
public class PriceDimensionDTO
{
    private String    currency;
    private float     salesPrice;
    private InventDim inventDim;
}
