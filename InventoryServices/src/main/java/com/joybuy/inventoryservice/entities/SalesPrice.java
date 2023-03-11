package com.joybuy.inventoryservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data @Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "salesprice")
public class SalesPrice
{
    @Id
    @Column(name="productid")
    private String productId;
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
}
