package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	Inventory findByBook(Book book);

}
