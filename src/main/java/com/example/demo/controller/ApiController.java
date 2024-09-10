package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
public class ApiController {
	
	@Autowired
	private UserService userService;
	
	// 檢查phone有沒有被註冊過
	@PostMapping("/checkPhone")
    public ResponseEntity<Map<String, Boolean>> checkPhone(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        boolean exists = userService.checkPhoneExist(phoneNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

}
