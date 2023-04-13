package com.joybuy.inventoryservice.DTO;

import com.joybuy.inventoryservice.entities.Product;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@ToString
public class ProductPriceDimensionsDTO
{
    private String productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private String productImage;
    private String productLink;
    private String brand;
    private List<PriceDimensionDTO> priceDimensionDTO;
}
