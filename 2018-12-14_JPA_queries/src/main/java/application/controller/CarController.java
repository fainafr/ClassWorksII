package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.CarEntity;
import application.model.ICarModel;
import dto.Car;

@RestController
public class CarController {
	
	@Autowired
	ICarModel carModel;
	
	@PostMapping("/addRandomCars")
	public boolean addRandomCars(@RequestParam("number") int num) {
		for (int i=0; i<num; i++) carModel.addCar(new CarEntity(Car.getRandomCar()));
		return true;
	}
	
	@GetMapping("/getById")
	public Car getCarById(@RequestParam("id") int id){
		return carModel.getCarById(id);
	}
	
	@GetMapping("/getByModel")
	public List<Car> getCarsByModel(@RequestParam("model") String model){
		return carModel.getCarsByModel(model);
	}
	
	@GetMapping("/getByModelOrderedByProduction")
	public List<Car> getCarsByYearOrderByModel(@RequestParam("model") String model){
		return carModel.getCarsByModelOrderByProduction(model);
	}
	
	@GetMapping("/getByEngineInRange")
	public List<Car> getByEngineInRange(@RequestParam("from") double engineFrom, @RequestParam("to") double engineTo){
		return carModel.getCarsByEngineRange(engineFrom, engineTo);
	}
	
	@GetMapping("/getBySomeQuery")
	public List<Car> getBySomeQuery(@RequestParam("model") String model,
									@RequestParam("from") double engineMin, 
			                        @RequestParam("to") double engineMax){
		return carModel.getBySomeQuery(model, engineMin, engineMax);
	}

	

}
