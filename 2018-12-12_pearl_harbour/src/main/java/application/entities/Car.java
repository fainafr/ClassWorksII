package application.entities;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="car_table") //persistance
public class Car {
	
	private static final int NUM_MODELS = 10;
	private static final int MIN_YEAR = 1980;
	private static final int MAX_YEAR = 2018;
	private static final double MIN_ENGINE = 1.6;
	private static final double MAX_ENGINE = 2.5;
	private static final double AC_PROBABILITY = 0.5;

	static Random gen = new Random();
	
	@Id
	private int id;
	
	private String model;
	private int year;
	private double engine;
	private boolean ac;
	
	//@Transient
	//private int number;  // trying to disable a field; 
	
	public Car(String model, int year, double engine, boolean ac) {
		super();
		this.model = model;
		this.year = year;
		this.engine = engine;
		this.ac = ac;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", year=" + year + ", engine=" + engine + ", ac=" + ac + "]";
	}
	
	public String toInsertString() {
		return "('"+model+"',"+year+","+engine+","+(ac ? 1 : 0)+")";
	}
	
	public static Car getRandomCar() {
		String randomModel = "model#"+gen.nextInt(NUM_MODELS);
		int randomYear = MIN_YEAR + gen.nextInt(MAX_YEAR-MIN_YEAR+1);
		double randomEngine = ((int)((MIN_ENGINE + gen.nextDouble()*(MAX_ENGINE-MIN_ENGINE))*100.))/100.;
		boolean randomAC = gen.nextDouble() < AC_PROBABILITY;
		
		return new Car(randomModel, randomYear, randomEngine, randomAC);
	}
	
	
}
