package application.model;

import java.time.LocalDate;
import java.util.List;

import application.entities.Car;

public interface ICarModel {
	
	public boolean addCar(Car car);
	public void addAll(List<Car> garage);
	public List<Car> getAll();
	public Car getCarById(int id);
	public boolean changeProduction(int id, LocalDate production);
	public boolean removeCar(int id);
	
}
