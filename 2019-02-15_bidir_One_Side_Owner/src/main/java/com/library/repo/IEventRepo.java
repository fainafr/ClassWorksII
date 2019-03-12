package com.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.IndependentEvent;

public interface IEventRepo extends JpaRepository<IndependentEvent, String> {
	
}