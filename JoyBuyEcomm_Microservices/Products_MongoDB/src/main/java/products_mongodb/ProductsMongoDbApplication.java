package products_mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class ProductsMongoDbApplication
{
    private static final Logger logger = LogManager.getLogger(ProductsMongoDbApplication.class);
    public static void main(String[] args) 
    {
        SpringApplication.run(ProductsMongoDbApplication.class, args);

        // Log a debug message
        logger.debug("Log4J2 debug message");

        // Log an info message
        logger.info("Log4J2 info message");

        // Log an error message with an exception
        try {
            throw new Exception("This is an exception");
        } catch (Exception e) {
            logger.error("Log4J2 error message", e);
        }
    }

}
