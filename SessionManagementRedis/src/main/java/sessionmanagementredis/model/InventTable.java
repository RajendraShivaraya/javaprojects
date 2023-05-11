package sessionmanagementredis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class InventTable implements Serializable
{

    @Id
    public String itemId;
    private String itemName;
    private int    qty;
    private Date   expiryDate;
    private double itemPrice;
    private Date   manufactureDate;
    private AllEnums.ItemCategory itemCategory;
    private AllEnums.ItemSubCategory itemSubCategory;
}
