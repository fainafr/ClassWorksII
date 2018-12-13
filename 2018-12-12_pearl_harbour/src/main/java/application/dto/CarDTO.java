package application.dto;

import lombok.Data;

@Data
public class CarDTO {
	
	private int id;
	private String model;
	private int year;
	private double engine;
	private boolean ac;
	
}
