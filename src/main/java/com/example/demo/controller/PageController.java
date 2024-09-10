package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
public class PageController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "index";
	}
	
	// 登入註冊頁面
	@GetMapping("/loginregister.controller")
	public String loginregisterAction() {
		return "login";
	}
	

}
