package com.joybuy.inventoryservice.services;

import com.joybuy.inventoryservice.DTO.PriceDimensionDTO;
import com.joybuy.inventoryservice.DTO.ProductPriceDimensionsDTO;
import com.joybuy.inventoryservice.entities.*;
import com.joybuy.inventoryservice.repository.IInventDimRepository;
import com.joybuy.inventoryservice.repository.IProductRepository;
import com.joybuy.inventoryservice.repository.ISalesPriceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor @NoArgsConstructor
public class ProductServices
{
    @Autowired
    public IProductRepository productRepository;
    @Autowired
    public IInventDimRepository inventDimRepository;
    @Autowired
    public ISalesPriceRepository salesPriceRepository;

    public Product getProduct(String productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }


    public ResponseEntity<String> createProduct(ProductPriceDimensionsDTO productPriceDimensionsDTO)
    {
        try
        {
            Product createProduct = new Product();
            createProduct.setId(productPriceDimensionsDTO.getProductId());
            createProduct.setProductLink(productPriceDimensionsDTO.getProductLink());
            createProduct.setProductImage(productPriceDimensionsDTO.getProductImage());
            createProduct.setProductCategory(productPriceDimensionsDTO.getProductCategory());
            createProduct.setProductDescription(productPriceDimensionsDTO.getProductDescription());
            createProduct.setProductName(productPriceDimensionsDTO.getProductName());
            createProduct.setBrand(productPriceDimensionsDTO.getBrand());
            productRepository.save(createProduct);

            for (PriceDimensionDTO priceDimensionDTO : productPriceDimensionsDTO.getPriceDimensionDTO())
            {

                InventDim inventDim = new InventDim();
                inventDim.setInventColorId(priceDimensionDTO.getInventDim().getInventColorId());
                inventDim.setInventSizeId(priceDimensionDTO.getInventDim().getInventSizeId());
                inventDim.setProduct(createProduct);
                Long dimId = inventDimRepository.save(inventDim).getDimId();

                SalesPrice price = new SalesPrice();

                price.setSalesPrice(priceDimensionDTO.getSalesPrice());
                price.setCurrency(priceDimensionDTO.getCurrency());
                price.setInventDim(inventDimRepository.findById(dimId).get());
                price.setProduct(createProduct);

                salesPriceRepository.save(price);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception er)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er.getMessage());
        }
    }
}
