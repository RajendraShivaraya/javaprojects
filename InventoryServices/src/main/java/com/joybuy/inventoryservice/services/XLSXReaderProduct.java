package com.joybuy.inventoryservice.services;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import com.joybuy.inventoryservice.entities.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class XLSXReaderProduct implements Runnable
{

    public static void main(String[] args)
    {
        XLSXReaderProduct test = new XLSXReaderProduct();
        test.run();
    }

    @Override
    public void run()
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
                prodObj.setProductDescription(row.getCell(2).getStringCellValue());
                prodObj.setProductCategory(row.getCell(3).getStringCellValue());
                prodObj.setProductImage(row.getCell(4).getStringCellValue());
                prodObj.setProductLink(row.getCell(5).getStringCellValue());
                prodObj.setBrand(row.getCell(6).getStringCellValue());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
