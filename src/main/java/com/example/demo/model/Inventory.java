package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "inventory")
public class Inventory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
	
	@OneToOne
    @JoinColumn(name = "isbn")
    private Book book;
	
	private LocalDateTime storeTime;
	private String status;
	
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<BorrowingRecord> borrowingRecord = new ArrayList<>();
	

	public Inventory() {
	}


	public Integer getInventoryId() {
		return inventoryId;
	}


	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDateTime getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BorrowingRecord> getBorrowingRecord() {
		return borrowingRecord;
	}

	public void setBorrowingRecord(List<BorrowingRecord> borrowingRecord) {
		this.borrowingRecord = borrowingRecord;
	}
	
}
