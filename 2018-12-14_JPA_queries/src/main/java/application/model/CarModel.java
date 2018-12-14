package application.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.CarEntity;
import application.repo.CarJpaRepository;
import dto.Car;

@Service
public class CarModel implements ICarModel{
	
	@Autowired
	CarJpaRepository carRepo;

	@Override
	@Transactional
	public boolean addCar(CarEntity car) {
		System.out.println(car.getId());
		System.out.println(carRepo.existsById(car.getId()));
		if(carRepo.existsById(car.getId())) return false;
		carRepo.save(car);
		return true;
	}
	
	public Car getCarById(int id) {
		return carRepo.findById(id).orElse(null).getCar();
	}
	
	private static List<Car> mapToCars(List<CarEntity> list){
		return list.stream().map((ce)->{return ce.getCar();}).collect(Collectors.toList());
	}

	@Override
	public List<Car> getCarsByModel(String model) {
		return mapToCars(carRepo.findByModel(model));
	}

	@Override
	public List<Car> getCarsByModelOrderByProduction(String model) {
		return mapToCars(carRepo.findByModelOrderByProduction(model));
	}

	@Override
	public List<Car> getCarsByEngineRange(double engineFrom, double engineTo) {
		return mapToCars(carRepo.findByEngineBetween(engineFrom, engineTo));
	}

	@Override
	public List<Car> getBySomeQuery(String model, double engineMin, double engineMax) {
		return mapToCars(carRepo.getBySomeQuery(model, engineMin, engineMax));
	}

	@Override
	@Transactional
	public boolean deleteCar(CarEntity car) {
	
		if(!carRepo.existsById(car.getId())) return false;
		carRepo.delete(car);
		return true;
		
	}

	@Override
	public boolean deleteCarCollection(Collection<CarEntity> cars) {

		boolean res = true; 
		for (CarEntity car : cars){
			res = deleteCar(car) && res;
		}
		return res;
		
	}

	@Override
	public CarEntity update(CarEntity car) {
		
		return null;
		//TODO: stub
		
	}
	
	@Override
	public boolean addAll(Collection<CarEntity> cars){
		boolean res = true; 
		for (CarEntity car : cars){
			res = addCar(car) && res;
		}
		return res;
	}
	
	@Override
	public boolean containsAll(List<CarEntity> cars) {
		
		boolean res = true; 
		for (CarEntity car : cars){
			res = carRepo.existsById(car.getId()) && res;
		}
		return res;
	}

	
	
	

	
}
