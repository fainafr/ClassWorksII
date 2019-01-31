package com.h2hacks.bitdiddle.H2_BOOT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.h2hacks.bitdiddle.H2_BOOT.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
