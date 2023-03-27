package com.joybuy.paymentservice.repository;

import com.joybuy.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, String>
{
    Payment findBySalesIdAndCustId(String salesId, String custId);
}
