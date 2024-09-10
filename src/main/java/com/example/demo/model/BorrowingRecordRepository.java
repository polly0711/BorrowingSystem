package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
	
	@Query(value = "SELECT recordId, userId, inventoryId, borrowingTime, returnTime FROM BorrowingRecord WHERE userId = :userId AND returnTime IS NULL", nativeQuery = true)
	List<Object[]> findByUserIdAndReturnTimeIsNull(
        @Param("userId") Integer userId
    );

}
