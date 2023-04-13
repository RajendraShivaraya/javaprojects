package com.joybuy.paymentservice.service;

import com.joybuy.paymentservice.DTO.Enums;
import com.joybuy.paymentservice.DTO.SalesOrderDTO;
import com.joybuy.paymentservice.entity.Payment;
import com.joybuy.paymentservice.entity.PaymentTrans;
import com.joybuy.paymentservice.repository.IPaymentRepository;
import com.joybuy.paymentservice.repository.IPaymentTransRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PaymentService
{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private IPaymentTransRepository paymentTransRepository;

    @Transactional
    public ResponseEntity<String> processSalesOrderPayment(SalesOrderDTO salesOrder)
    {
        Payment payment = paymentRepository.findBySalesIdAndCustId(salesOrder.getSalesId(), salesOrder.getCustId());

        if (payment == null)
        {
            payment = modelMapper.map(salesOrder, Payment.class);
            payment.setPaymentId("PAY-"+ LocalDateTime.now());
            paymentRepository.save(payment);
        }

        PaymentTrans paymTrans = new PaymentTrans();
        paymTrans.setPayment(payment);
        paymTrans.setTransDate(new Date());
        paymTrans.setPaymentAmount(salesOrder.getSalesAmount());
        paymTrans.setPaymentstatus(Enums.PaymentStatus.Authorized);
        paymTrans.setCardNumber("1234-5678-9876-5432");
        paymTrans.setAuthorization("adjfsui237864ghje094835439984238g3625ggu4735g");
        paymTrans.setCardExpiryDate("06/27");
        paymTrans.setCardSecurity("123");
        paymentTransRepository.save(paymTrans);

        JSONObject responseBody = new JSONObject();
        responseBody.put("Status", paymTrans.getPaymentstatus());
        responseBody.put("AmountCharged", paymTrans.getPaymentAmount());

        return new ResponseEntity<>(responseBody.toString(), HttpStatus.OK);
    }
}
