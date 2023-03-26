package com.joybuy.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@FieldNameConstants
@Table(name = "salesprice")
public class SalesPrice
{

    @Id
    @Column(name="recid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recid;

    @Column(name="salesprice")
    private float salesPrice;
    @Column(name="pruchprice")
    private float purchPrice;
    @Column(name="invprice")
    private float invPrice;
    @Column(name="discprice")
    private float discPrice;
    @Column(name="currency", length = 4)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name="productid", referencedColumnName = "id", nullable = false)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventdim", referencedColumnName = "dimid")
    private InventDim inventDim;
}
