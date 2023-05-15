package sessionmanagementredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sessionmanagementredis.model.AllEnums;
import sessionmanagementredis.model.InventTable;
import sessionmanagementredis.model.InventTableRepository;
import sessionmanagementredis.model.ItemCategoryDTO;
import sessionmanagementredis.service.InventService;

import java.util.List;

@RestController
public class InventoryController
{
    @Autowired
    InventService inventService;

    @PostMapping("/items/create")
    public String createItems(@RequestBody List<InventTable> inventTables)
    {
        return inventService.createItems(inventTables);
    }

    @GetMapping("/items/")
    public ResponseEntity<List<InventTable>> findAll()
    {
        return new ResponseEntity<>(inventService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/items/category/{category}")
    public ResponseEntity<List<InventTable>> findByItemCategory(@PathVariable AllEnums.ItemCategory category)
    {
        return new ResponseEntity<>(inventService.findByItemCategory(category), HttpStatus.OK);
    }
    @GetMapping("/items/subcategory/{subcategory}")
    public ResponseEntity<List<InventTable>> findByItemSubCategory(@PathVariable AllEnums.ItemSubCategory subcategory)
    {
        return new ResponseEntity<>(inventService.findByItemSubCategory(subcategory), HttpStatus.OK);
    }
    @GetMapping("/items/qty/{qty}")
    public ResponseEntity<List<InventTable>> findByQtyGreaterThanEqual(@PathVariable Integer qty)
    {
        return new ResponseEntity<>(inventService.findByQtyGreaterThanEqual(qty), HttpStatus.OK);
    }
    @GetMapping("/items/price/{price}")
    public ResponseEntity<List<InventTable>> findByItemPriceGreaterThanEqual(@PathVariable Double price)
    {
        return new ResponseEntity<>(inventService.findByItemPriceGreaterThanEqual(price), HttpStatus.OK);
    }
    @GetMapping("/items/categoryDTO/")
    public ResponseEntity<List<InventTable>> findByItemCategoryAndItemSubCategory(@RequestBody ItemCategoryDTO categoryDTO)
    {
        return new ResponseEntity<>(inventService.findByItemCategoryAndItemSubCategory(categoryDTO), HttpStatus.OK);
    }

    @PutMapping("/items/updateqty/{itemid}")
    public ResponseEntity<InventTable> updateItem(@PathVariable String itemid)
    {
        return new ResponseEntity<>(inventService.updateItemQty(itemid), HttpStatus.OK);
    }
}
