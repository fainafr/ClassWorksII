package application.controller;

import application.dto.CarDTO;
import application.entities.Car;

public class CarAdapter {

	public static Car toCar(CarDTO carDTO) {
		return new Car(carDTO.getModel(), carDTO.getYear(), carDTO.getEngine(),carDTO.isAc());
	}
	
}
