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

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#addRandomBook()
	 */
    @Override
	@GetMapping("/randomBook")
    public boolean addRandomBook() {
        return libraryService.addRandomBook();
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getBook(long)
	 */
    @Override
	@GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return libraryService.getBook(id);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#addBook(com.library.entity.Book)
	 */
    @Override
	@PostMapping("/book")
    public boolean addBook(@RequestBody Book book) {
        return libraryService.add(book);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#deleteBook(long)
	 */
    @Override
	@DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable long id) {
        return libraryService.delete(id);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#updateBook(com.library.entity.Book)
	 */
    @Override
	@PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return libraryService.update(book);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAll()
	 */
    @Override
	@GetMapping("/get_all")
    public List <Book> getAll() {
        return libraryService.getAll();
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAllBooksByPublisher(java.lang.String)
	 */
    @Override
	@GetMapping("/get_all/publisher/{publishername}")
    public List <Book> getAllBooksByPublisher(@PathVariable String publisherName) {
        return libraryService.getAllBooksByPublisher(new Publisher(publisherName));
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAllBooksByAuthor(java.lang.String, java.lang.String)
	 */
    @Override
	@GetMapping("get_all/author/")
    public List <Book> getAllBooksByAuthor(@RequestParam String firstName,
                                           @RequestParam String lastName) {
        return libraryService.getAllBooksByAuthor(new Author(new AuthorId(firstName, lastName)));
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAllBooksBetweenEditon(java.time.LocalDate, java.time.LocalDate)
	 */
    @Override
	@GetMapping("get_all/edition")
    public List <Book> getAllBooksBetweenEditon(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return libraryService.getAllBooksBetweenEdition(from, to);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAllBooksBetweenPrice(double, double)
	 */
    @Override
	@GetMapping("get_all/price")
    public List <Book> getAllBooksBetweenPrice(@RequestParam double from,
                                               @RequestParam double to) {
        return libraryService.getAllBooksBetweenPrice(from, to);
    }

    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#fillRepository(int)
	 */
    @Override
	@PostMapping("/fill_repo")
    public List <Book> fillRepository(@RequestParam int amount) {
        return libraryService.fillRepository(amount);
    }


    /* (non-Javadoc)
	 * @see com.library.controller.ILibraryController#getAllPublishersByCountry(java.lang.String)
	 */
    @Override
	@GetMapping("/publisher")
    public List<Publisher> getAllPublishersByCountry(@RequestParam String country){
        return libraryService.getAllPublishersByCountry(country);
    }
}
