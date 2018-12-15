package com.library.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Publisher;

public interface IBookRepo extends JpaRepository<Book, Long>{
//	List<Book> findBooksByPublisher(Publisher publisher);
//	List<Book> findBooksByAuthors(Author author);
//	List<Book> findBooksByEditionBetween(LocalDate from, LocalDate to);
//	List<Book> findBooksByPriceBetween(double min, double max);
}
