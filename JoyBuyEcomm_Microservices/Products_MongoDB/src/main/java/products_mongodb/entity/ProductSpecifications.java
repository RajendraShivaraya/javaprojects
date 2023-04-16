package products_mongodb.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductSpecifications
{
    public String defaultSpec;
    public String technicalSpec;
    public String shippingSpec;
    public String handlingSpec;
    public String otherSpec;
}
