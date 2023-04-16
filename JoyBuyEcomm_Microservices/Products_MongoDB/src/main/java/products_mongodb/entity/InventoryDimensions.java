package products_mongodb.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InventoryDimensions
{
    public String color;
    public String size;
    public String  weight;
    public String dimension;
    public String serialNumber;
    public String batchNumber;
}
