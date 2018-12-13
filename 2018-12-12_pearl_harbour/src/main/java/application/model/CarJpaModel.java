package application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.Car;
import application.repo.CarJpaRepo;

@Service
public class CarJpaModel implements ICarJpaModel {
	
	@Autowired
	CarJpaRepo carRepo;
	
	/* (non-Javadoc)
	 * @see application.model.ICarJpaModel#getAll()
	 */
	@Override
	public List<Car> getAll(){
		List<Car> lst = carRepo.findAll();
		System.out.println(lst);
		return carRepo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see application.model.ICarJpaModel#create(application.entities.Car)
	 */
	@Override
	public void create(Car car){
		carRepo.saveAndFlush(car);
	}
	
	/* (non-Javadoc)
	 * @see application.model.ICarJpaModel#read(int)
	 */
	@Override
	public Car read(int id){
		return carRepo.findById(id).get();
	}
	
	/* (non-Javadoc)
	 * @see application.model.ICarJpaModel#update(application.entities.Car)
	 */
	@Override
	public void update(Car car){
		carRepo.saveAndFlush(car);
	}
	
	/* (non-Javadoc)
	 * @see application.model.ICarJpaModel#delete(application.entities.Car)
	 */
	@Override
	public void delete(Car car){
		carRepo.delete(car);
	}
	
	
	/*
	 * you can create annotations that define relations; 
	 */
}
