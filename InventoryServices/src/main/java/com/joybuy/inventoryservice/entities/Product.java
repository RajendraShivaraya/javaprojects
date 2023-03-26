package com.joybuy.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@FieldNameConstants
@ToString
@Table(name = "products")
public class Product
{
    @Id
    @Column(name="id")
    private String id;
    @Column(name="productname")
    private String productName;
    @Column(name="productdescription", columnDefinition = "varchar(1000)")
    private String productDescription;
    @Column(name="productcategory")
    private String productCategory;
    @Column(name="productimage", columnDefinition = "varchar(1000)")
    private String productImage;
    @Column(name="productlink", columnDefinition = "varchar(1000)")
    private String productLink;
    @Column(name="brand")
    private String brand;

    @OneToMany(mappedBy="product", fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<SalesPrice> salesPrices = new ArrayList<>();

    @OneToMany(mappedBy="product", fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    public List<InventDim> inventDims = new ArrayList<>();
}
