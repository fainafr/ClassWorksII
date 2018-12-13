package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Country;

public interface ICountryRepo extends JpaRepository<Country, String>{

}
