package com.joybuy.inventoryservice.controller;

import com.joybuy.inventoryservice.services.ItemServices;
import com.joybuy.inventoryservice.services.ProductServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemController
{
    @Autowired
    public ItemServices itemServices;


    @PostMapping(value = "/inventoryservices/getitemdetails", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> getItemDetails(@RequestBody Map<String, Object> inputData)
    {
        return itemServices.getItemDetails(inputData);
    }

}
