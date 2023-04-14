package com.joybuy.inventoryservice.controller;

import com.joybuy.inventoryservice.DTO.ProductPriceDimensionsDTO;
import com.joybuy.inventoryservice.entities.*;
import com.joybuy.inventoryservice.services.ProductServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor @NoArgsConstructor
public class ProductController
{
    @Autowired
    public ProductServices productService;

    @GetMapping("/")
    public String rootPath()
    {
        return "<h1> Welcome to inventory services</h1>";
    }

    @PostMapping(value = "/getitemdetails", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> getItemDetails(@RequestBody Map<String, Object> inputData)
    {
        return productService.getItemDetails(inputData);
    }
    @GetMapping("/jsonpayload/{entity}")
    public Object getJsonPayloadOfEntity(@PathVariable String entity)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException
    {
        try
        {
            Class<?> productEntity = Class.forName("com.joybuy.inventoryservice.entities." + entity); //"com.joybuy.inventoryservice.entities.Product");
            return productEntity.getDeclaredConstructor().newInstance();
        }
        catch(Exception e)
        {
            throw new ClassNotFoundException("Entity " + entity+ " could not be found");
        }
    }



    @GetMapping("/products")
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProducts(@PathVariable String id)
    {
        return productService.productRepository.findById(id).get();
    }

    @PostMapping("/createproduct")
    public Product createProduct(@RequestBody Product product)
    {
        return productService.productRepository.save(product);
    }

    @PostMapping("/createproductdimprice")
    public ResponseEntity<String> createProduct(@RequestBody ProductPriceDimensionsDTO product)
    {
        return productService.createProduct(product);
    }

}
