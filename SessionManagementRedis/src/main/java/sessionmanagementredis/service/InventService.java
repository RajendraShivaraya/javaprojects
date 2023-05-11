package sessionmanagementredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import sessionmanagementredis.model.AllEnums;
import sessionmanagementredis.model.InventTable;
import sessionmanagementredis.model.InventTableRepository;
import sessionmanagementredis.model.ItemCategoryDTO;

import java.util.List;

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
    @Cacheable(key = "")
    public List<InventTable> findAll()
    {return inventTableRepository.findAll();}

    @Cacheable(key = "#category")
    public List<InventTable> findByItemCategory(AllEnums.ItemCategory category)
    {return inventTableRepository.findByItemCategory(category);}

    @Cacheable(key = "#p0")
    public List<InventTable> findByItemSubCategory(AllEnums.ItemSubCategory subCategory)
    {return inventTableRepository.findByItemSubCategory(subCategory);}

    @Cacheable(key = "#qty")
    public List<InventTable> findByQtyGreaterThanEqual(Integer qty)
    {return inventTableRepository.findByQtyGreaterThanEqual(qty);}

    @Cacheable(key = "#price")
    public List<InventTable> findByItemPriceGreaterThanEqual(Double price)
    {return inventTableRepository.findByItemPriceGreaterThanEqual(price);}
    @Cacheable(key = "#categoryDTO")
    public List<InventTable> findByItemCategoryAndItemSubCategory(ItemCategoryDTO categoryDTO)
    {return inventTableRepository.findByItemCategoryAndItemSubCategory(categoryDTO.getCategory(), categoryDTO.getSubCategory());}

}
