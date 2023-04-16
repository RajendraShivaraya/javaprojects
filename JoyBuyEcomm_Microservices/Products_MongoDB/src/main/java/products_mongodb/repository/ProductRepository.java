package products_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import products_mongodb.entity.Products;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Products, String>
{
    public Products findByProductName(String productName);
    public List<Products> findByCategory(String category);
}
