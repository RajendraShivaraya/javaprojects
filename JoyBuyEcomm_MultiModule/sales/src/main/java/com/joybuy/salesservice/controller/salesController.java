package com.joybuy.salesservice.controller;

import com.joybuy.salesservice.DTO.CustomMessageDTO;
import com.joybuy.salesservice.DTO.SalesOrderRequestDTO;
import com.joybuy.salesservice.DTO.SalesOrderResponseDTO;
import com.joybuy.salesservice.entities.Enums;
import com.joybuy.salesservice.service.SalesOrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class salesController
{
    @Autowired
    public SalesOrderService salesOrderService;
    private  static final String SALES_SERVICE = "salesservice";
    @PostMapping("/createsalesorder")
    @CircuitBreaker(name = SALES_SERVICE, fallbackMethod = "createSalesOrderFallback")
    public CustomMessageDTO createSalesOrder(@RequestBody SalesOrderRequestDTO salesOrderDTO)
    {
        return salesOrderService.createSalesOrder(salesOrderDTO);
    }
    private CustomMessageDTO createSalesOrderFallback(@RequestBody SalesOrderRequestDTO salesOrderDTO)
    {
        CustomMessageDTO responseBody = new CustomMessageDTO();

        responseBody.setRequestStatus(Enums.RequestStatus.ServiceFailure);
        responseBody.setResponseMessage("Could not create Sales Order - Contact Admin");
        responseBody.setResponseObject(null);

        return responseBody;
    }

    @PostMapping(value = "/invoicesalesorder", produces = "application/json; charset=utf-8")
    public CustomMessageDTO invoiceSalesOrder(@RequestBody Map<String, String> inputData)
    {
        return salesOrderService.invoiceSalesOrder(inputData);
    }

    @GetMapping("/")
    public String rootPath()
    {
        return "<h1> Welcome to sales services</h1>";
    }
}
