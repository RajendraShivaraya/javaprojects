package sessionmanagementredis.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventTableRepository extends JpaRepository<InventTable, String>
{
    public List<InventTable> findByItemCategory(AllEnums.ItemCategory category);
    public List<InventTable> findByItemSubCategory(AllEnums.ItemSubCategory subCategory);
    public List<InventTable> findByQtyGreaterThanEqual(Integer qty);
    public List<InventTable> findByItemPriceGreaterThanEqual(Double price);
    public List<InventTable> findByItemCategoryAndItemSubCategory(AllEnums.ItemCategory category, AllEnums.ItemSubCategory subCategory);

}
