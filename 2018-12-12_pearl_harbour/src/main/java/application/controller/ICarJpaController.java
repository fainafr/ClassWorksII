package application.controller;

import java.util.List;

import application.entities.Car;

public interface ICarJpaController {

	
	List<Car> getAll();
	
	void create(Car car);

	void read(int Id);

	void update(Car car);

	void delete(Car car);

}