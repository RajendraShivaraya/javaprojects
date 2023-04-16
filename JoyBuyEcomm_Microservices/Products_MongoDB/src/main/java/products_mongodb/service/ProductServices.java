package products_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import products_mongodb.entity.InventoryDimensions;
import products_mongodb.entity.ProductRatings;
import products_mongodb.entity.ProductSpecifications;
import products_mongodb.entity.Products;
import products_mongodb.repository.ProductRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ProductServices
{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private SaveImageToGCP saveImageToGCP;

    public String createProduct(Products product) throws IOException
    {
        product.setGcpImageURL(saveImageToGCP.uploadImage(product.imageURL, product.id));
        productRepository.save(product);
        return "Product " + product.id + "create successfully";
    }

    public String bulkCreateProduct()
    {
        //String message = saveImageToGCP.getMyBeanMessage();
        //return message;

        //File file = new File("C:\\Products.xlsx");   //creating a new file instance
        File file = new File(new File("./data/AmazonProducts1000.xlsx").getAbsolutePath());

        Products prodObj = null;
        try (FileInputStream fis = new FileInputStream(file)) //obtaining bytes from the file
        {
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file

            // Skip first row
            if (itr.hasNext()) {
                itr.next();
            }
            while (itr.hasNext())
            {
                Row row = itr.next();
                prodObj = new Products();
                prodObj.setId(row.getCell(0).getStringCellValue());
                prodObj.setProductName(row.getCell(1).getStringCellValue());
                prodObj.setBrandName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "Apple");
                prodObj.setCategory(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "Electronics");
                prodObj.setPrice(row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : 10.45F);
                prodObj.setModelNumber(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "Model001");
                prodObj.setImageURL(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : "www.test.com");
                prodObj.setProductSpecifications(
                        ProductSpecifications
                                .builder()
                                .defaultSpec(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : "")
                                .shippingSpec(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : "")
                                .technicalSpec(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : "")
                                .otherSpec("")
                                .build());
                prodObj.setInventoryDimensions(
                        InventoryDimensions
                                .builder()
                                .color(row.getCell(10) != null ? row.getCell(10).getStringCellValue() : "")
                                .size(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : "")
                                .weight(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "")
                                .dimension(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : "")
                                .serialNumber(row.getCell(14) != null ? row.getCell(14).getStringCellValue() : "")
                                .batchNumber(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : "")
                                .build()
                );
                prodObj.setProductRatings(
                        ProductRatings
                                .builder()
                                .averageRating((int) row.getCell(16).getNumericCellValue())
                                .fiveStar((int) row.getCell(17).getNumericCellValue())
                                .fourStar((int) row.getCell(18).getNumericCellValue())
                                .threeStar((int) row.getCell(19).getNumericCellValue())
                                .twoStar((int) row.getCell(20).getNumericCellValue())
                                .oneStar((int) row.getCell(21).getNumericCellValue())
                                .build()
                );
                prodObj.setSku(row.getCell(22).getStringCellValue());
                prodObj.setStock(row.getCell(23).getBooleanCellValue());
                prodObj.setAvailableQty((int) row.getCell(24).getNumericCellValue());
                prodObj.setGcpImageURL(saveImageToGCP.uploadImage(prodObj.imageURL, prodObj.id));

                productRepository.save(prodObj);
            }
        }
        catch (Exception e)
        {
            return "Product creation failed, " + e.getMessage() ;
        }
        return "Products created successfully";
    }

    public ResponseEntity<Products> getProduct(String productId)
    {
        Optional<Products> productsOptional = productRepository.findById(productId);
        if (productsOptional.isPresent())
            return new ResponseEntity<>(productsOptional.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Products>> getProducts()
    {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.NOT_FOUND);
    }
}
