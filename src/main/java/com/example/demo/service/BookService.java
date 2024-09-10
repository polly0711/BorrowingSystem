package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
	
	public Book findById(String isbn) {
		Optional<Book> optional = bookRepo.findById(isbn);
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

}
