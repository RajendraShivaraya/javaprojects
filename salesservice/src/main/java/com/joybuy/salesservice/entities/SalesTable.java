package com.joybuy.salesservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "salestable")
public class SalesTable
{
    @Id
    @Column(name = "salesid")
    public String SalesId;
    @Column(name = "custid")
    public String custId;
    @Column(name = "salesamount")
    public float  salesAmount;
    @Column(name = "salestax")
    public float  salesTax;
    @Column(name = "dlvaddress")
    public String dlvAddress;
    @Column(name = "transdate")
    public Date   transDate;
    @Column(name = "receiptid")
    public String receiptId;
    @Column(name = "salestype")
    public Enums.SalesType salesType;

    @OneToMany(mappedBy="salesTable", fetch= FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    public List<SalesLine> salesLines = new ArrayList<>();;
}
