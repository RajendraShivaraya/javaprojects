package com.joybuy.inventoryservice.controller;

import com.joybuy.inventoryservice.entities.*;
import com.joybuy.inventoryservice.services.ProductServices;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@AllArgsConstructor @NoArgsConstructor
public class ProductController
{
    @Autowired
    public ProductServices productService;

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

    @GetMapping("/product/{id}")
    public Product getProducts(@PathVariable String id)
    {
        return productService.productRepository.findById(id).get();
    }

    @GetMapping("/products")
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }

    @PostMapping("/newProduct")
    public Product createProduct(@RequestBody Product product)
    {
        return productService.productRepository.save(product);
    }

    @GetMapping("/item/{productId}")
    public InventoryServices getItem(@PathVariable String productId)
    {
        return productService.getItem(productId);
    }
    @GetMapping("/items")
    public InventoryServices getItems()
    {
        return productService.getItems();
    }

    @GetMapping("/readItems")
    public String insertBulkProducts()
    {
        productService.insertBulkProducts();
        productService.insertBulkSalesPrice();
        productService.insertBulkInventDim();

        return "Data Import Complete";
    }
}
