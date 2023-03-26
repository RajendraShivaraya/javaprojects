package com.joybuy.salesservice.controller;

import com.joybuy.salesservice.DTO.SalesOrderRequestDTO;
import com.joybuy.salesservice.DTO.SalesOrderResponseDTO;
import com.joybuy.salesservice.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class salesController
{
    @Autowired
    public SalesOrderService salesOrderService;

    @PostMapping("/salesservice/createsalesorder")
    public String createSalesOrder(@RequestBody SalesOrderRequestDTO salesOrderDTO) //SalesOrderResponseDTO
    {
        return salesOrderService.createSalesOrder(salesOrderDTO);
    }
}
