package com.joybuy.inventoryservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data @Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "products")
public class Product
{
    @Id
    @Column(name="productid")
    private String productId;
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
}
