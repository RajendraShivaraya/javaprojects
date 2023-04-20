package products_mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "products")
public class Products
{
    @Id
    @Field("product_id")
    public String id;
    @Field("product_name")
    public String productName;
    @Field("product_brandName")
    public String brandName;
    @Field("product_category")
    public String category;
    @Field("product_price")
    public double price;
    @Field("product_modelNumber")
    public String modelNumber;
    @Field("product_sku")
    public String sku;
    @Field("product_cloudImageURL")
    public String cloudImageURL;
    @Field("product_imageURL")
    public String imageURL;
    @Field("product_stock")
    public boolean stock;
    @Field("product_availableQty")
    public Integer availableQty;
    @Field("product_productSpecifications")
    public ProductSpecifications productSpecifications;
    @Field("product_inventoryDimensions")
    public InventoryDimensions inventoryDimensions;
    @Field("product_productRatings")
    public ProductRatings productRatings;
}
