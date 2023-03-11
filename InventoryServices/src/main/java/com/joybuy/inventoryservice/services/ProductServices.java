package com.joybuy.inventoryservice.services;

import com.joybuy.inventoryservice.entities.*;
import com.joybuy.inventoryservice.repository.IInventDimRepository;
import com.joybuy.inventoryservice.repository.IProductRepository;
import com.joybuy.inventoryservice.repository.ISalesPriceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@AllArgsConstructor @NoArgsConstructor
public class ProductServices
{
    @Autowired
    public IProductRepository productRepository;
    @Autowired
    public IInventDimRepository inventDimRepository;
    @Autowired
    public ISalesPriceRepository salesPriceRepository;

    public Product getProduct(String productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public InventoryServices getItem(String productId)
    {
        List<Product> prod = Arrays.asList(productRepository.findById(productId).get());
        List<InventDim> invDim = inventDimRepository.findByProductId(productId);
        List<SalesPrice> salesPrice = Arrays.asList(salesPriceRepository.findById(productId).get());
        return new InventoryServices(
                prod,
                salesPrice,
                invDim
                );
    }

    public InventoryServices getItems()
    {
        List<Product> prod = productRepository.findAll();
        List<InventDim> invDim = inventDimRepository.findAll();
        List<SalesPrice> salesPrice = salesPriceRepository.findAll();
        return new InventoryServices(
                prod,
                salesPrice,
                invDim
        );
    }

    public void insertBulkProducts()
    {
        try
        {
            File file = new File("C:\\Products.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                Product prodObj = new Product();
                prodObj.setProductId(row.getCell(0).getStringCellValue());
                prodObj.setProductName(row.getCell(1).getStringCellValue());
                String text = row.getCell(2).getStringCellValue();
                prodObj.setProductDescription(text.length() <= 999 ? text : text.substring(0, 999));
                prodObj.setProductCategory(row.getCell(3).getStringCellValue());
                //prodObj.setProductImage(row.getCell(4).getStringCellValue());
                String text1 = row.getCell(5).getStringCellValue();
                prodObj.setProductLink(text1.length() <= 999 ? text1 : text1.substring(0, 999));
                prodObj.setBrand(row.getCell(6).getStringCellValue());
                productRepository.save(prodObj);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void insertBulkSalesPrice()
    {
        try
        {
            File file = new File("C:\\SalesPrice.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                SalesPrice salesPriceObj = new SalesPrice();
                salesPriceObj.setProductId(row.getCell(0).getStringCellValue());
                salesPriceObj.setSalesPrice((float) row.getCell(1).getNumericCellValue());
                salesPriceObj.setInvPrice((float) row.getCell(2).getNumericCellValue());
                salesPriceObj.setCurrency(row.getCell(3).getStringCellValue());
                salesPriceRepository.save(salesPriceObj);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void insertBulkInventDim()
    {
        try
        {
            File file = new File("C:\\InventDim.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                InventDim dimObj = new InventDim();
                dimObj.setProductId(row.getCell(0).getStringCellValue());
                dimObj.setInventColorId(row.getCell(1).getStringCellValue());
                dimObj.setAvailableQty((float)row.getCell(2).getNumericCellValue());
                dimObj.setInventLocationId(row.getCell(3).getStringCellValue());
                inventDimRepository.save(dimObj);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
