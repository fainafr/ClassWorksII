package application.model;

import java.util.Collection;
import java.util.List;

import application.entities.CarEntity;
import dto.Car;

public interface ICarModel {
	public boolean addCar(CarEntity car);
	
	public Car getCarById(int id);
	public List<Car> getCarsByModel(String model);
	public List<Car> getCarsByModelOrderByProduction(String model);
	public List<Car> getCarsByEngineRange(double engineFrom, double engineTo);
	public List<Car> getBySomeQuery(String model, double engineMin, double engineMax);
	
	public boolean deleteCar(CarEntity car);
	
	public boolean deleteCarCollection(Collection<CarEntity> cars);
	
	public CarEntity update(CarEntity car);

	boolean addAll(Collection<CarEntity> cars);

	boolean containsAll(List<CarEntity> cars);

}
