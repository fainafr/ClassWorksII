package com.mtmapp.repo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtmapp.entities.Author;


public interface IAuthorRepo extends JpaRepository<Author, String> {
	
}
