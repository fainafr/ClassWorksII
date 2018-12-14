package dto;

import java.time.LocalDate;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Car {
	
	private static final int NUM_MODELS = 10;
	private static final int MIN_YEAR = 1980;
	private static final int MAX_YEAR = 2018;
	private static final double MIN_ENGINE = 1.6;
	private static final double MAX_ENGINE = 2.5;
	private static final double AC_PROBABILITY = 0.5;

	static Random gen = new Random();
	
	private String model;
	private LocalDate productionDate;
	private double engine;
	private boolean ac;
	

	@Override
	public String toString() {
		return "Car [model=" + model + ", productionDate=" + productionDate + ", engine=" + engine + ", ac=" + ac + "]";
	}
	
	
	public static Car getRandomCar() {
		String randomModel = "model_"+gen.nextInt(NUM_MODELS);
		
		long fromEpochDay = LocalDate.of(MIN_YEAR, 1, 1).toEpochDay();
		long toEpochDay   = LocalDate.of(MAX_YEAR, 12, 31).toEpochDay();
		LocalDate randomProductionDate =
				LocalDate.ofEpochDay(fromEpochDay + (long)(gen.nextDouble()*(toEpochDay - fromEpochDay + 1)));
		
		double randomEngine = ((int)((MIN_ENGINE + gen.nextDouble()*(MAX_ENGINE-MIN_ENGINE))*100.))/100.;
		boolean randomAC = gen.nextDouble() < AC_PROBABILITY;
		
		return new Car(randomModel, randomProductionDate, randomEngine, randomAC);
	}
	
	
}
