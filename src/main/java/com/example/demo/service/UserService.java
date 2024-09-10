package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User saveUser(User newUser) {
		String encodedPwd = passwordEncoder.encode(newUser.getPassword()); // 加密
		newUser.setPassword(encodedPwd); // 存加密過後的
		return userRepo.save(newUser);
	}
	
	// 檢查有沒有被註冊過
	public boolean checkPhoneExist(String phoneNumber) {
		
		User dbMember = userRepo.findByPhoneNumber(phoneNumber);
		
		if(dbMember == null) {
			return false;
		}
		return true;
	}
	
	// 確認帳密是否正確
	public User checkLogin(String phoneNumber, String password) {
		
		User dbMember = userRepo.findByPhoneNumber(phoneNumber);
		
		if(dbMember == null) {
			return null;
		} else {
			String dbPassword = dbMember.getPassword();
			boolean result = passwordEncoder.matches(password, dbPassword); // .matches()可以比對加密過後密碼
			
			if(result) return dbMember;
			return null;
		}
	}
	
	public User findById(Integer userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

}
