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


    public SalesOrderResponseDTO createSalesOrder(SalesOrderRequestDTO salesOrderRequestDTO)
    {
        SalesOrderResponseDTO salesOrderResponseDTO = new SalesOrderResponseDTO();
        SalesTable salesTable = new SalesTable();
        salesTable.setSalesId("SO-"+ LocalDateTime.now());
        salesTable.setCustId(salesOrderRequestDTO.getCustId());
        salesTable.setSalesType(Enums.SalesType.InStore);
        salesTable.setDlvAddress("5050 Beverly");
        salesTable.setTransDate(new Date());
        salesTable.setStatus(Enums.SalesStatus.Created);
        salesTable.setAmountPaid(0F);

        int lineNumber = 0;
        float orderSalesAmount = 0;
        float orderDiscount = 0F;
        for (ItemsDTO items: salesOrderRequestDTO.getItems())
        {
            lineNumber++;
            JSONObject responseBody = getItemDetails(items.itemId, items.inventColorId, items.inventSizeId);

            SalesLine salesLine = new SalesLine();
            salesLine.setSalesTable(salesTable);
            salesLine.setItemId(items.itemId);
            salesLine.setLineNum(lineNumber);
            salesLine.setTransId("Trans" + LocalDateTime.now());
            salesLine.setInventDimId(responseBody.getLong("inventDim"));
            salesLine.setQty(items.qty);
            salesLine.setSalesPrice(responseBody.getFloat("salesPrice"));
            salesLine.setLineAmount(salesLine.qty * salesLine.salesPrice );
            salesLine.setLineDisc(items.lineDisc);
            salesLine.setTotalPrice(salesLine.lineAmount - salesLine.lineDisc);
            salesTable.getSalesLines().add(salesLine);
            orderSalesAmount += salesLine.lineAmount;
            orderDiscount += salesLine.lineDisc;
        }

        salesTable.setSalesTax(0F);
        salesTable.setSalesAmount(orderSalesAmount);
        salesTable.setSalesDiscount(orderDiscount);
        salesTable.setTotalPrice(salesTable.salesAmount - salesTable.salesDiscount);
        salesTableRepository.save(salesTable);

        return convertSalesTableEntityToResponseDTO(salesTableRepository.findById(salesTable.getSalesId()).get());
    }

    private SalesOrderResponseDTO convertSalesTableEntityToResponseDTO(SalesTable salesTable)
    {
        SalesOrderResponseDTO salesOrderResponseDTO = new SalesOrderResponseDTO();

        salesOrderResponseDTO.setSalesId(salesTable.getSalesId());
        salesOrderResponseDTO.setCustId(salesTable.getCustId());
        salesOrderResponseDTO.setDlvAddress(salesTable.getDlvAddress());
        salesOrderResponseDTO.setTransDate(salesTable.getTransDate());
        salesOrderResponseDTO.setReceiptId(salesTable.getReceiptId());
        salesOrderResponseDTO.setSalesType(salesTable.getSalesType());
        salesOrderResponseDTO.setStatus(salesTable.getStatus());
        salesOrderResponseDTO.setSalesAmount(salesTable.getSalesAmount());
        salesOrderResponseDTO.setSalesDiscount(salesTable.getSalesDiscount());
        salesOrderResponseDTO.setTotalPrice(salesTable.getTotalPrice());
        salesOrderResponseDTO.setSalesTax(salesTable.getSalesTax());
        salesOrderResponseDTO.setAmountPaid(salesTable.getAmountPaid());

        salesOrderResponseDTO.setSalesLines(convertSalesLineEntityListToDTOList(salesTable.getSalesLines()));
        return salesOrderResponseDTO;
    }
    private SalesLineDTO convertSalesLineEntityToDTO(SalesLine salesLine)
    {
        SalesLineDTO salesLineDTO = new SalesLineDTO();

        salesLineDTO.setLineNum(salesLine.getLineNum());
        salesLineDTO.setItemId(salesLine.getItemId());
        salesLineDTO.setQty(salesLine.getQty());
        salesLineDTO.setSalesPrice(salesLine.getSalesPrice());
        salesLineDTO.setLineAmount(salesLine.getLineAmount());
        salesLineDTO.setLineDisc(salesLine.getLineDisc());
        salesLineDTO.setTotalPrice(salesLine.getTotalPrice());
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
