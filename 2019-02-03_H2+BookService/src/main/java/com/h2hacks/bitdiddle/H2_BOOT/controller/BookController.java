package com.h2hacks.bitdiddle.H2_BOOT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h2hacks.bitdiddle.H2_BOOT.entity.Book;
import com.h2hacks.bitdiddle.H2_BOOT.model.BookModel;

@RestController
@RequestMapping(value="/h2library")
public class BookController {

	@Autowired
	private BookModel model; 
	
	@GetMapping("/getAll")
    public List<Book> getAll() {
        return model.getAll();
    }
	
	@PostMapping("/add")
	public void addBook(@RequestParam(value = "id") int id,	@RequestParam(value = "name") String name) {
        model.add(id, name);
    }
}
