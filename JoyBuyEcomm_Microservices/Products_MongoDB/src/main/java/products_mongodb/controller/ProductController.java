package products_mongodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import products_mongodb.entity.Products;
import products_mongodb.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

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
    public String createProduct(@RequestBody Products product) throws IOException
    {
        return productServices.createProduct(product);
    }

    @PostMapping("/product/bulkcreateproduct")
    public String createProduct()
    {
        return productServices.bulkCreateProduct();
    }

    @GetMapping("/product/{productid}")
    public ResponseEntity<Products> getProduct(@PathVariable String productid)
    {
        return productServices.getProduct(productid);
    }

    @GetMapping("/product/")
    public ResponseEntity<List<Products>> getProduct()
    {
        return productServices.getProducts();
    }
}
