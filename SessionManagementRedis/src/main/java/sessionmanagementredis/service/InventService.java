package sessionmanagementredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import sessionmanagementredis.model.AllEnums;
import sessionmanagementredis.model.InventTable;
import sessionmanagementredis.model.InventTableRepository;
import sessionmanagementredis.model.ItemCategoryDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"Inventory"})
public class InventService
{
    @Autowired
    InventTableRepository inventTableRepository;

    @CacheEvict(allEntries = true)
    public String createItems(List<InventTable> inventTables)
    {
        inventTables.stream()
                .forEach(item -> inventTableRepository.save(item));
        return "All the items inserted successfully";
    }
    @Cacheable(key = "'AllItems'")
    public List<InventTable> findAll()
    {return inventTableRepository.findAll();}

    @Cacheable(key = "#category")
    public List<InventTable> findByItemCategory(AllEnums.ItemCategory category)
    {return inventTableRepository.findByItemCategory(category);}

    @Cacheable(key = "#subcategory")
    public List<InventTable> findByItemSubCategory(AllEnums.ItemSubCategory subcategory)
    {return inventTableRepository.findByItemSubCategory(subcategory);}

    @Cacheable(key = "#qty")
    public List<InventTable> findByQtyGreaterThanEqual(Integer qty)
    {return inventTableRepository.findByQtyGreaterThanEqual(qty);}

    @Cacheable(key = "#price")
    public List<InventTable> findByItemPriceGreaterThanEqual(Double price)
    {return inventTableRepository.findByItemPriceGreaterThanEqual(price);}
    @Cacheable(key = "#categoryDTO")
    public List<InventTable> findByItemCategoryAndItemSubCategory(ItemCategoryDTO categoryDTO)
    {return inventTableRepository.findByItemCategoryAndItemSubCategory(categoryDTO.getCategory(), categoryDTO.getSubCategory());}

    @CachePut(key = "#itemid")
    public InventTable updateItemQty(String itemid)
    {
        try
        {
            Optional<InventTable> optionalInventTable= inventTableRepository.findById(itemid);
            if (optionalInventTable.isPresent())
            {
                InventTable inventTable = optionalInventTable.get();
                inventTable.setQty(inventTable.getQty()-1);
                return inventTableRepository.save(inventTable);
            }
            else
                return null;
        }
        catch (Exception e) {
            return null;
        }
    }
}
