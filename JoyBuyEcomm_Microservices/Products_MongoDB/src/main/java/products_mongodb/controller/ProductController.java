package products_mongodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import products_mongodb.entity.Products;
import products_mongodb.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import products_mongodb.service.SaveImageToAWS;

@RestController
public class ProductController
{
    @Autowired
    ProductServices productServices;

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

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

    @PostMapping("/product/bulkcreateproduct/{cloudProvider}")
    public String createProduct(@PathVariable String cloudProvider)
    {
        return productServices.bulkCreateProduct(cloudProvider);
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

    @GetMapping("/logs")
    public String logPage()
    {
        // pre-java 8
        if (logger.isDebugEnabled()) {
            logger.debug("Hello from Log4j 2 - num : {}", num);
        }

        logger.debug("Hello from Log4j2 debug - num : {}", () -> num);
        logger.info( "Hello from Log4j2 info  - num : {}", () -> num);
        logger.error("Hello from Log4j2 error - num : {}", () -> num);
        logger.fatal("Hello from Log4j2 fatal - num : {}", () -> num);

        return "welcome"; //view
    }

    @Autowired
    SaveImageToAWS saveImageToAWS;
    @GetMapping("/aws")
    public void awsOps() throws IOException
    {
        saveImageToAWS.uploadImage("", "");
    }
}
