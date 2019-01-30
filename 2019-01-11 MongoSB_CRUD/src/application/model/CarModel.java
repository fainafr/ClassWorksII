package application.model;

import java.time.LocalDate;
import java.util.List;
///*****
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.Car;
import application.repo.CarMongoRepository;

@Service
public class CarModel implements ICarModel{
	
	@Autowired
	CarMongoRepository cars;

	@Override
	public boolean addCar(Car car) {
		if(cars.existsById(car.getId())) return false;
		cars.save(car);
		return true;
	}

	@Override
	public void addAll(List<Car> garage) {
		cars.saveAll(garage);		
	}

	@Override
	public List<Car> getAll() {
		return cars.findAll();
	}

	@Override
	public Car getCarById(int id) {
		return cars.findById(id).orElse(null);
	}

	@Override
	public boolean changeProduction(int id, LocalDate production) {
		Car car = cars.findById(id).orElse(null);
		if (car == null) return false;
		car.setProduction(production);
		cars.save(car);
		return true;
	}

	@Override
	public boolean removeCar(int id) {
		Car car = cars.findById(id).orElse(null);
		if (car == null) return false;
		cars.delete(car);
		return true;
	}

}
