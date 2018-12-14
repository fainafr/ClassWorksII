package manual_test;

import application.entities.CarEntity;
import dto.Car;

public class ManualTest {
	private  static CarEntity car1;
	
	public static void main(String[] args){
		
		car1 = new CarEntity(Car.getRandomCar());
		System.out.println(car1);
		
		System.out.println(Car.getRandomCar());
	}
}
