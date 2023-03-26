package com.joybuy.inventoryservice.services;

import com.joybuy.inventoryservice.entities.InventDim;
import com.joybuy.inventoryservice.entities.SalesPrice;
import com.joybuy.inventoryservice.repository.IInventDimRepository;
import com.joybuy.inventoryservice.repository.ISalesPriceRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServices
{
    @Autowired
    public IInventDimRepository inventDimRepository;
    @Autowired
    public ISalesPriceRepository salesPriceRepository;

    // Takes JSON as input and returns JSON as output
    public ResponseEntity<String> getItemDetails(Map<String, Object> inputData)
    {
        // Deserialize

        Object itemId = inputData.get("itemId");
        Object colorId = inputData.get("colorId");
        Object sizeId = inputData.get("sizeId");

        InventDim inventDim = inventDimRepository.findByProductIdAndInventColorIdAndInventSizeId(itemId.toString(), colorId.toString(), sizeId.toString()).get(0);
        // Serialize
        JSONObject responseBody = new JSONObject();

        if (inventDim != null)
        {
            SalesPrice salesPrice = salesPriceRepository.findById(inventDim.getDimId()).get();
            if (salesPrice != null)
            {
                responseBody.put(SalesPrice.Fields.salesPrice, salesPrice.getSalesPrice());
                responseBody.put(SalesPrice.Fields.currency, salesPrice.getCurrency());
                responseBody.put(SalesPrice.Fields.invPrice, salesPrice.getInvPrice());
                responseBody.put(SalesPrice.Fields.inventDim, salesPrice.getInventDim().getDimId());
            }
        }
        else
        {
            responseBody.put("Error", "Could not find item details");
        }
        return new ResponseEntity<>(responseBody.toString(), HttpStatus.OK);
    }
}
