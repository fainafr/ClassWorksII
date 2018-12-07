package application.model;

import java.util.List;

import application.entities.Car;

public interface ICarJpaModel {

	List<Car> getAll();

	void create(Car car);

	Car read(int id);

	void update(Car car);

	void delete(Car car);

}