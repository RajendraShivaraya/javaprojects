package kafkademo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "OrderLines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLines
{
    @MongoId
    public Long   RecId;
    public String orderId;
    public String customerId;
    public String itemId;
    public String itemDescription;
    public double qty;
    public String note;
    public double salesPrice;
    public double tax;
}
