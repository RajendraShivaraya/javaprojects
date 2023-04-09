package com.joybuy.inventoryservice.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joybuy.inventoryservice.DTO.PriceDimensionDTO;
import com.joybuy.inventoryservice.DTO.ProductPriceDimensionsDTO;
import com.joybuy.inventoryservice.entities.*;
import com.joybuy.inventoryservice.repository.IInventDimRepository;
import com.joybuy.inventoryservice.repository.IProductRepository;
import com.joybuy.inventoryservice.repository.ISalesPriceRepository;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
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

    public ResponseEntity<String> getItemDetails(Map<String, Object> inputData)
    {
        // Deserialize

        Object itemId = inputData.get("itemId");
        Object colorId = inputData.get("colorId");
        Object sizeId = inputData.get("sizeId");

        InventDim inventDim = inventDimRepository.findByProductIdAndInventColorIdAndInventSizeId(itemId.toString(), colorId.toString(), sizeId.toString()).get(0);
        // Serialize
        JSONObject responseBody = new JSONObject();

        if (inventDim != null)
        {
            SalesPrice salesPrice = salesPriceRepository.findById(inventDim.getDimId()).get();
            if (salesPrice != null)
            {
                responseBody.put(SalesPrice.Fields.salesPrice, salesPrice.getSalesPrice());
                responseBody.put(SalesPrice.Fields.currency, salesPrice.getCurrency());
                responseBody.put(SalesPrice.Fields.invPrice, salesPrice.getInvPrice());
                responseBody.put(SalesPrice.Fields.inventDim, salesPrice.getInventDim().getDimId());
                Optional<Product> optionalProduct = productRepository.findById(inventDim.getProduct().getId());
                if (optionalProduct.isPresent())
                {
                    Product product = optionalProduct.get();
                    ProductPriceDimensionsDTO prodPriceDTO = new ModelMapper().map(product, ProductPriceDimensionsDTO.class);
                    responseBody.put("Product", new Gson().toJson(prodPriceDTO));
                }
            }
        }
        else
        {
            responseBody.put("Error", "Could not find item details");
        }
        return new ResponseEntity<>(responseBody.toString(), HttpStatus.OK);
    }
}
