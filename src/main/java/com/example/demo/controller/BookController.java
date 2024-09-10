package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowingRecord;
import com.example.demo.model.Inventory;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.InventoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private BookService bookService;

	// 借書
	@GetMapping("/borrow.controller")
	public String borrowAction(@RequestParam("inventoryId") Integer inventoryId, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		Inventory inventory = inventoryService.findById(inventoryId);
		inventoryService.borrowBook(user, inventory);
		
		return "redirect:/home";
	}
	
	// 還書
	@GetMapping("/return.controller")
	public String returnAction(@RequestParam("recordId") Integer recordId) {
		inventoryService.returnBook(recordId);
		
		return "redirect:/myBooks.controller";
	}
	
	// 我的書房
	@GetMapping("/myBooks.controller")
	public String myBooksAction(HttpSession httpSession, Model model) {
		User user = (User) httpSession.getAttribute("user");
		List<BorrowingRecord> records = inventoryService.getMyBooks(user.getUserId());
		model.addAttribute("records", records);
		return "myBooks";
	}
	
	// 查詢一本書
	@GetMapping("/getonebook.controller")
	public String getonememberAction(@RequestParam("isbn") String isbn, Model model) {
		Book book = bookService.findById(isbn);
		Inventory inventory = inventoryService.findByIsbn(isbn);
		model.addAttribute("book", book);
		model.addAttribute("inventory", inventory);
		return "getOneBook";
	}

}
