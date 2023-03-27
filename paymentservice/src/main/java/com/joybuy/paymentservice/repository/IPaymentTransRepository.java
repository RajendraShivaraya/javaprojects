package com.joybuy.paymentservice.repository;

import com.joybuy.paymentservice.entity.PaymentTrans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentTransRepository extends JpaRepository<PaymentTrans, Long>
{
}
