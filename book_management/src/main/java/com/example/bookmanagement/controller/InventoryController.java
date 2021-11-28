package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Inventory;
import com.example.bookmanagement.service.inventory.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @PostMapping
    public Inventory save(@RequestBody Inventory inventory){
        return inventoryService.save(inventory);
    }
}
