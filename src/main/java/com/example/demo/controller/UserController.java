package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 註冊
	@PostMapping("/registerPost.controller")
	public String registerAction(@RequestParam("phoneNumber") String phoneNumber, 
			@RequestParam("password") String password, 
			@RequestParam("userName") String userName
			) {
		User newUser = new User();
		newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.setUserName(userName);
        newUser.setRegistrationTime(LocalDateTime.now());
 		userService.saveUser(newUser);
 		return "redirect:/loginregister.controller?success=true";
	}
	
	// 登入
	@PostMapping("/loginPost.controller")
	public String loginAction(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password,HttpSession httpSession, Model model) {
		User user = null;
		if((user = userService.checkLogin(phoneNumber, password)) != null) {
            httpSession.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/home";
        } else {
        	return "redirect:/loginregister.controller?error=true";
        }
	}
	
	// 登出
	@GetMapping("/logout.controller")
	public String logoutAction(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/home";
	}

}
