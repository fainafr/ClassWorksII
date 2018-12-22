package com.mtmapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtmapp.entities.Book;

public interface IBookRepo extends JpaRepository<Book, String> {

}
