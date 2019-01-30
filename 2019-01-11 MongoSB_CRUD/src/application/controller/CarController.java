package application.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.Car;
import application.model.ICarModel;

@RestController
public class CarController {

	@Autowired
	ICarModel model;
	
	@PostMapping(value="/addCar")
	public boolean addCar(@RequestBody Car car) { return model.addCar(car);}
	
	@PostMapping(value="/addAll")
	public void addAll(@RequestBody List<Car> garage) { model.addAll(garage);}
	
	@GetMapping(value="/getAll")
	public List<Car> getAll(){return model.getAll();}
	
	@GetMapping(value="/getById")
	public Car getById(@RequestParam(name="id") int id){return model.getCarById(id);}
	
	@PostMapping(value="/changeProduction")
	public boolean changeProduction(@RequestParam(name="id") int id, @RequestBody LocalDate production){
		return model.changeProduction(id, production);
	}
	
	@PostMapping(value="/removeCar")
	public boolean removeCar(@RequestParam(name="id") int id) {return model.removeCar(id);}

}
