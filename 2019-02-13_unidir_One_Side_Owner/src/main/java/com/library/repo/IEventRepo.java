package com.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Event;

public interface IEventRepo extends JpaRepository<Event, String> {
	
	List<Event> findPublishersByCountryName(String countryName);

}