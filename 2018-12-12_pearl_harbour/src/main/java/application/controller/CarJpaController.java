package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.Car;
import application.model.ICarJpaModel;

@RestController
@RequestMapping(value = "/car")
public class CarJpaController implements ICarJpaController {

	@Autowired
	ICarJpaModel model;

	
	@Override
	@GetMapping(value = "/getall", produces = "application/json")
	public List<Car> getAll(){
		return model.getAll();
	}
	
	/* (non-Javadoc)
	 * @see application.controller.ICarJpaController#create(application.dto.CarDTO)
	 */
	@Override
	@PostMapping(value = "/create", produces = "application/json")
	public void create(@RequestBody Car car) {
		model.create(car);
	}

	/* (non-Javadoc)
	 * @see application.controller.ICarJpaController#read(int)
	 */
	@Override
	@GetMapping(value = "/read", produces = "application/json")
	public void read(@RequestParam(value = "ID") int Id) {
		
	}

	/* (non-Javadoc)
	 * @see application.controller.ICarJpaController#update(application.dto.CarDTO)
	 */
	@Override
	@PostMapping(value = "/update", produces = "application/json")
	public void update(@RequestBody Car car) {
		model.create(car);
	}

	/* (non-Javadoc)
	 * @see application.controller.ICarJpaController#delete(application.dto.CarDTO)
	 */
	@Override
	@PostMapping(value = "/delete", produces = "application/json")
	public void delete(@RequestBody Car car) {
		model.delete(car);
	}

}
