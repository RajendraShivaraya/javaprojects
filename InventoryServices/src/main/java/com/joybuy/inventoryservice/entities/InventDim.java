package com.joybuy.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Data @Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "inventdim")
public class InventDim
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventdimid")
    private Long inventDimId;
    @Column(name="productid")
    private String productId;
    @Column(name="configid")
    private String configId;
    @Column(name="inventSizeId")
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
}
