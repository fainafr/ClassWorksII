package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Author;
import com.library.entity.AuthorId;

public interface IAuthorRepo extends JpaRepository<Author, AuthorId> {

}
