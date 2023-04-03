package com.joybuy.paymentservice.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.joybuy.paymentservice.DTO.OrderPaymentDTO;
import com.joybuy.paymentservice.DTO.SalesOrderDTO;
import com.joybuy.paymentservice.service.PaymentService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/paymentservice/invoicesalesorder", method = RequestMethod.POST)
    private ResponseEntity<String> processPayment(@RequestBody Object salesOrder)
    {
        // Convert incoming object to DTO
        SalesOrderDTO salesOrderDTO = modelMapper.map(salesOrder, SalesOrderDTO.class);

        return paymentService.processSalesOrderPayment(salesOrderDTO);
    }

    @GetMapping("/")
    public String rootPath()
    {
        return "<h1> Welcome to payment services</h1>" ;
    }
}
