package com.joybuy.joybuyecomm_microservices.repository;

import com.joybuy.joybuyecomm_microservices.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Products, String>
{
    public Products findByProductName(String productName);
    public List<Products> findByCategory(String category);
}
