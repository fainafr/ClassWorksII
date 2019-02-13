package com.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Employee;

public interface IEmployeeRepo extends JpaRepository<Employee, String> {
	
	List<Employee> findPublishersByCountryName(String countryName);

}