package kafkademo.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.*;
import java.util.List;

@Document(collection = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Orders
{
    @MongoId
    @Field
    public String           orderId;
    public String           customerId;
    public LocalDate        orderDate;
    public LocalTime        orderTime;
    public LocalDateTime    orderDateTime;
    public double           salesAmount;
    public double           taxAmount;
    public double           orderAmount;
    public List<OrderLines> orderItems;
    public Integer          phNumber;
    public JoybuyEnums.OrderStatus orderStatus;
}
