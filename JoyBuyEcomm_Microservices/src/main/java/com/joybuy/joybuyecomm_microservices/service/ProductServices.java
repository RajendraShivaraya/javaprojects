package com.joybuy.joybuyecomm_microservices.service;

import com.joybuy.joybuyecomm_microservices.entity.Products;
import com.joybuy.joybuyecomm_microservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices
{
    @Autowired
    ProductRepository productRepository;

    public String createProduct(Products product)
    {
        productRepository.save(product);
        return "Product " + product.id + "create successfully";
    }
}
