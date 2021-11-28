package com.example.bookmanagement.repository.inventory;

import com.example.bookmanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

}
