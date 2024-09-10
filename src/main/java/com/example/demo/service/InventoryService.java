package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowingRecord;
import com.example.demo.model.BorrowingRecordRepository;
import com.example.demo.model.Inventory;
import com.example.demo.model.InventoryRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

@Service
public class InventoryService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private InventoryRepository inventoryRepo;
	@Autowired
	private BookService bookService;
	@Autowired
	private BorrowingRecordService borrowingRecordService;
	@Autowired
    private BorrowingRecordRepository borrowingRecordRepo;
	
	public Inventory findById(Integer inventoryId) {
		Optional<Inventory> optional = inventoryRepo.findById(inventoryId);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
	public Inventory findByIsbn(String isbn) {
		Book book = bookService.findById(isbn);
		return inventoryRepo.findByBook(book);
	}
	
	// 借書
	@Transactional
	@Modifying
	public void borrowBook(User user, Inventory inventory) {
		BorrowingRecord newBorrowing = new BorrowingRecord();
		newBorrowing.setUser(user);
		newBorrowing.setInventory(inventory);
		newBorrowing.setBorrowingTime(LocalDateTime.now());
		borrowingRecordRepo.save(newBorrowing);
		inventory.setStatus("已借閱");
	}
	
	// 還書
	@Transactional
	@Modifying
	public void returnBook(Integer recordId) {
		BorrowingRecord record = borrowingRecordService.findById(recordId);
		record.setReturnTime(LocalDateTime.now());
		Inventory inventory = findById(record.getInventory().getInventoryId());
		inventory.setStatus("可借閱");
	}
	
	// 手動轉換
	public List<BorrowingRecord> getMyBooks(Integer userId){
		List<Object[]> results = borrowingRecordRepo.findByUserIdAndReturnTimeIsNull(userId);
		List<BorrowingRecord> borrowingRecords = new ArrayList<>();
		for (Object[] record : results) {
			BorrowingRecord borrowingRecord = new BorrowingRecord();
			// 手動將 Object[] 中的值提取並設置到 BorrowingRecord 中
            Integer recordId = (Integer) record[0];  // assuming it's the first field
            Integer userIdFromDB = (Integer) record[1]; // assuming it's the second field (userId)
            Integer inventoryId = (Integer) record[2]; // assuming it's the third field (inventoryId)
            // 將 Timestamp 轉換為 LocalDateTime
            Timestamp borrowingTimestamp = (Timestamp) record[3]; // 取得借書時間 (Timestamp)
            LocalDateTime borrowingTime = borrowingTimestamp.toLocalDateTime(); // 轉換為 LocalDateTime
            
            Timestamp returnTimestamp = (Timestamp) record[4]; // 取得還書時間 (可能為 null)
            LocalDateTime returnTime = (returnTimestamp != null) ? returnTimestamp.toLocalDateTime() : null;
//            LocalDateTime borrowingTime = (LocalDateTime) record[3]; // borrowingTime
//            LocalDateTime returnTime = (LocalDateTime) record[4]; // returnTime
            
	        // 根據 userId 和 inventoryId 查找對應的對象
	        User user = userRepo.findById(userIdFromDB).orElse(null);
	        Inventory inventory = inventoryRepo.findById(inventoryId).orElse(null);
	            
	        // 將值設置到 BorrowingRecord 對象中
	        borrowingRecord.setRecordId(recordId);
	        borrowingRecord.setUser(user);
	        borrowingRecord.setInventory(inventory);
	        borrowingRecord.setBorrowingTime(borrowingTime);
	        borrowingRecord.setReturnTime(returnTime);
	        // 添加到結果列表
	        borrowingRecords.add(borrowingRecord);
        }
        return borrowingRecords;
	}

}
