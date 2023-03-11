package com.joybuy.inventoryservice.entities;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class InventoryServices
{
    public List<Product> product;
    public List<SalesPrice> salesPrice;
    public List<InventDim> inventDim;
}
