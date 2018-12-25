package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Author;

public interface IAuthorRepo extends JpaRepository<Author, Long> {

}
