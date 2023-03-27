package com.joybuy.paymentservice.entity;

import com.joybuy.paymentservice.DTO.Enums;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;

@Entity
@Data @ToString
public class Payment
{
    @Id
    @Column(name = "paymentid")
    private String paymentId;
    @Column(name = "salesid")
    private String salesId;
    @Column(name = "custid")
    private String custId;
    @Column(name = "receiptid")
    private String receiptId;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentTrans> paymentTrans;
}
