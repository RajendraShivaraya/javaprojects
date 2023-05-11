package sessionmanagementredis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDTO
{
    private AllEnums.ItemCategory category;
    private AllEnums.ItemSubCategory subCategory;
}
