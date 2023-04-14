package com.joybuy.joybuyecomm_microservices.controller;

import com.joybuy.joybuyecomm_microservices.entity.Products;
import com.joybuy.joybuyecomm_microservices.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    @Autowired
    ProductServices productServices;

    @GetMapping("/")
    public String home()
    {
        return "<h1>Welcome to Joybuy Ecomm using NOSQL</h1>";
    }

    @PostMapping("/product/createproduct")
    public String createProduct(@RequestBody Products product)
    {
        return productServices.createProduct(product);
    }
}
