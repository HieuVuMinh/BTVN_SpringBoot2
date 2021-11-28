package com.example.bookmanagement.service.inventory;

import com.example.bookmanagement.model.Inventory;
import com.example.bookmanagement.repository.inventory.IInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private IInventoryRepository inventoryRepository;


    @Override
    public Iterable<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}
