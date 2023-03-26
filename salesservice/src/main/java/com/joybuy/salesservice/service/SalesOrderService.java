package com.joybuy.salesservice.service;

import com.joybuy.salesservice.DTO.ItemsDTO;
import com.joybuy.salesservice.DTO.SalesLineDTO;
import com.joybuy.salesservice.DTO.SalesOrderRequestDTO;
import com.joybuy.salesservice.DTO.SalesOrderResponseDTO;
import com.joybuy.salesservice.entities.Enums;
import com.joybuy.salesservice.entities.SalesLine;
import com.joybuy.salesservice.entities.SalesTable;
import com.joybuy.salesservice.repository.ISalesLineRepository;
import com.joybuy.salesservice.repository.ISalesTableRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SalesOrderService
{
    @Autowired
    private ISalesTableRepository   salesTableRepository;
    @Autowired
    private ISalesLineRepository    salesLineRepository;

    @Autowired
    RestTemplate restTemplate;


    public String createSalesOrder(SalesOrderRequestDTO salesOrderRequestDTO)
    {
        SalesOrderResponseDTO salesOrderResponseDTO = new SalesOrderResponseDTO();
        SalesTable salesTable = new SalesTable();
        salesTable.setSalesId("SO-"+ LocalDateTime.now());
        salesTable.setCustId(salesOrderRequestDTO.getCustId());
        salesTable.setSalesType(Enums.SalesType.Ecomm);
        salesTable.setSalesTax(0F);
        salesTable.setSalesAmount(0F);
        salesTable.setDlvAddress("5050 Beverly");
        salesTable.setTransDate(new Date());
        //salesTableRepository.save(salesTable);

        int lineNumber = 0;
        for (ItemsDTO items: salesOrderRequestDTO.getItems())
        {
            lineNumber++;
            JSONObject responseBody = getItemDetails(items.itemId, items.inventColorId, items.inventSizeId);

            SalesLine salesLine = new SalesLine();
            salesLine.setSalesTable(salesTable);
            salesLine.setItemId(items.itemId);
            salesLine.setSalesPrice(responseBody.getFloat("salesPrice"));
            salesLine.setLineDisc(items.lineDisc);
            salesLine.setLineNum(lineNumber);
            salesLine.setInventDimId(responseBody.getLong("inventDim"));
            salesLine.setQty(items.qty);
            salesLine.setTransId("Trans" + LocalDateTime.now());
            salesTable.getSalesLines().add(salesLine);
        }
        salesTableRepository.save(salesTable);

        return "Sales order created";
        //return convertSalesTableEntityToResponseDTO(salesTableRepository.findById(salesTable.getSalesId()).get());
    }

    private SalesOrderResponseDTO convertSalesTableEntityToResponseDTO(SalesTable salesTable)
    {
        SalesOrderResponseDTO salesOrderResponseDTO = new SalesOrderResponseDTO();
        salesOrderResponseDTO.setSalesId(salesTable.getSalesId());
        salesOrderResponseDTO.setCustId(salesTable.getCustId());
        salesOrderResponseDTO.setSalesAmount(salesTable.getSalesAmount());
        salesOrderResponseDTO.setSalesTax(salesTable.getSalesTax());
        salesOrderResponseDTO.setDlvAddress(salesTable.getDlvAddress());
        salesOrderResponseDTO.setReceiptId(salesTable.getReceiptId());
        salesOrderResponseDTO.setTransDate(salesTable.getTransDate());
        salesOrderResponseDTO.setSalesLines(convertSalesLineEntityListToDTOList(salesTable.getSalesLines()));
        return salesOrderResponseDTO;
    }
    private SalesLineDTO convertSalesLineEntityToDTO(SalesLine salesLine)
    {
        SalesLineDTO salesLineDTO = new SalesLineDTO();

        salesLineDTO.setLineDisc(salesLine.getLineDisc());
        salesLineDTO.setLineNum(salesLine.getLineNum());
        salesLineDTO.setSalesPrice(salesLine.getSalesPrice());
        salesLineDTO.setItemPrice(salesLine.getItemPrice());
        salesLineDTO.setItemId(salesLine.getItemId());
        salesLineDTO.setQty(salesLine.getQty());
        return salesLineDTO;
    }

    private List<SalesLineDTO> convertSalesLineEntityListToDTOList(List<SalesLine> salesLines)
    {
        List<SalesLineDTO> SalesLineDTOList = new ArrayList<>();

        for (SalesLine salesLine : salesLines )
        {
            SalesLineDTOList.add(convertSalesLineEntityToDTO(salesLine));
        }

        return SalesLineDTOList;
    }

    private JSONObject getItemDetails(String itemId, String colorId, String sizeId)
    {
        String resourceUrl = "http://localhost:8080/item/getdetails";


        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<String, Object>(3);
        requestBody.put("itemId", itemId);
        requestBody.put("colorId", colorId);
        requestBody.put("sizeId", sizeId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(requestBody, headers);


        String itemPriceResultAsJson = restTemplate.postForObject(resourceUrl, request, String.class);

        JSONObject jsonObj = new JSONObject(itemPriceResultAsJson);
        return jsonObj;
    }
}
