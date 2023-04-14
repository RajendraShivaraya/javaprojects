package com.joybuy.paymentservice.entity;

import com.joybuy.paymentservice.DTO.Enums;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "paymenttrans")
@Data
public class PaymentTrans
{
    @Id
    @Column(name = "recid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Payment.class)
    @JoinColumn(name="paymentid", referencedColumnName = "paymentid", nullable = false, foreignKey = @ForeignKey(name = "fk_payment"))
    private Payment payment;

    @Column(name = "transdate")
    private Date transDate;
    @Column(name = "paymentstatus")
    private Enums.PaymentStatus paymentstatus;
    @Column(name = "paymentamount")
    private float paymentAmount;
    @Column(name = "authorization")
    private String authorization;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "cardexpirydate")
    private String cardExpiryDate;
    @Column(name = "cardsecurity")
    private String cardSecurity;
}
