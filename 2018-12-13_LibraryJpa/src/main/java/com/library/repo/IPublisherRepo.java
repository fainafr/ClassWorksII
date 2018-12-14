package com.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Publisher;

public interface IPublisherRepo extends JpaRepository<Publisher, String> {
	
	List<Publisher> findPublishersByCountryName(String countryName);

}
