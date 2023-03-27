package com.joybuy.salesservice.controller;

import com.joybuy.salesservice.DTO.CustomMessageDTO;
import com.joybuy.salesservice.DTO.SalesOrderRequestDTO;
import com.joybuy.salesservice.DTO.SalesOrderResponseDTO;
import com.joybuy.salesservice.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class salesController
{
    @Autowired
    public SalesOrderService salesOrderService;

    @PostMapping("/salesservice/createsalesorder")
    private SalesOrderResponseDTO createSalesOrder(@RequestBody SalesOrderRequestDTO salesOrderDTO)
    {
        return salesOrderService.createSalesOrder(salesOrderDTO);
    }

    @PostMapping(value = "/salesservice/invoice", produces = "application/json; charset=utf-8")
    public CustomMessageDTO invoiceSalesOrder(@RequestBody Map<String, String> inputData)
    {
        return salesOrderService.invoiceSalesOrder(inputData);
    }
}
