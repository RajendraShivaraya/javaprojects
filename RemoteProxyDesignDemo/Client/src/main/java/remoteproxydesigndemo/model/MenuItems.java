package remoteproxydesigndemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuItems
{
    public String itemId;
    public String itemName;
    public String itemDescription;
    public Double itemPrice;
}
