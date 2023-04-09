package com.joybuy.inventoryservice.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@FieldNameConstants
@Table(name = "inventdim")
public class InventDim
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dimid")
    private Long dimId;

    @Column(name="configid")
    private String configId;
    @Column(name="inventsizeid")
    private String inventSizeId;
    @Column(name="inventcolorid")
    private String inventColorId;
    @Column(name="inventstyleid")
    private String inventStyleId;
    @Column(name="inventsiteid")
    private String inventSiteId;
    @Column(name="inventlocationid")
    private String inventLocationId;
    @Column(name="inventbatchid")
    private String inventBatchId;
    @Column(name="wmslocationid")
    private String wmsLocationId;
    @Column(name="wmspalletid")
    private String wmsPalletId;
    @Column(name="inventserialid")
    private String inventSerialId;
    @Column(name="avilableqty")
    private float availableQty;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name="productid", referencedColumnName = "id", nullable = false)
    private Product product;
}
