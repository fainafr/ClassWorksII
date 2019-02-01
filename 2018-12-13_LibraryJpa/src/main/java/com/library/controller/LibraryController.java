package com.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Author;
import com.library.entity.AuthorId;
import com.library.entity.Book;
import com.library.entity.Publisher;
import com.library.service.ILibraryService;

@RestController
public class LibraryController implements ILibraryController {

    @Autowired
    ILibraryService libraryService;

    @Override
	@GetMapping("/randomBook")
    public boolean addRandomBook() {
        return libraryService.addRandomBook();
    }

    @Override
	@GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return libraryService.getBook(id);
    }

    @Override
	@PostMapping("/book")
    public boolean addBook(@RequestBody Book book) {
        return libraryService.add(book);
    }

    @Override
	@DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable long id) {
        return libraryService.delete(id);
    }

    @Override
	@PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return libraryService.update(book);
    }

    @Override
	@GetMapping("/get_all")
    public List <Book> getAll() {
        return libraryService.getAll();
    }

    @Override
	@GetMapping("/get_all/publisher/{publishername}")
    public List <Book> getAllBooksByPublisher(@PathVariable String publisherName) {
        return libraryService.getAllBooksByPublisher(new Publisher(publisherName));
    }

    @Override
	@GetMapping("get_all/author/")
    public List <Book> getAllBooksByAuthor(@RequestParam String firstName,
                                           @RequestParam String lastName) {
        return libraryService.getAllBooksByAuthor(new Author(new AuthorId(firstName, lastName)));
    }

    @Override
	@GetMapping("get_all/edition")
    public List <Book> getAllBooksBetweenEditon(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return libraryService.getAllBooksBetweenEdition(from, to);
    }

    @Override
	@GetMapping("get_all/price")
    public List <Book> getAllBooksBetweenPrice(@RequestParam double from,
                                               @RequestParam double to) {
        return libraryService.getAllBooksBetweenPrice(from, to);
    }

    @Override
	@PostMapping("/fill_repo")
    public List <Book> fillRepository(@RequestParam int amount) {
        return libraryService.fillRepository(amount);
    }


    @Override
	@GetMapping("/publisher")
    public List<Publisher> getAllPublishersByCountry(@RequestParam String country){
        return libraryService.getAllPublishersByCountry(country);
    }

	@Override
	@PostMapping("/clear_repo")
	public boolean clear() {
		return libraryService.clearAll();
	}
}
