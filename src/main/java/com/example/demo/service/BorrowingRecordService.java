package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BorrowingRecord;
import com.example.demo.model.BorrowingRecordRepository;

@Service
public class BorrowingRecordService {
	
	@Autowired
	BorrowingRecordRepository borrowingRecordRepo;
	
	public BorrowingRecord findById(Integer recordId) {
		Optional<BorrowingRecord> optional = borrowingRecordRepo.findById(recordId);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

}
