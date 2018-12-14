package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import application.entities.CarEntity;
import application.model.CarModel;
import dto.Car;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueriesAppTest {

	public QueriesAppTest() {
	}

	private CarEntity car1;
	private CarEntity car2;
	private CarEntity car3;
	private List<CarEntity> cars;

	@Autowired
	private CarModel model;

	@Before
	public void setUp() {
		car1 = new CarEntity(Car.getRandomCar());
		car2 = new CarEntity(Car.getRandomCar());
		cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		car3 = new CarEntity(Car.getRandomCar());

	}

	@AfterEach
	public void tearDown() {
		model.deleteCarCollection(cars);
	}

	@Test(expected = NullPointerException.class)
	public void addNull() {
		model.addCar(null);
	}

	@Test(expected = AssertionError.class) // doesn't work in this project
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void add() {
		assertTrue(model.addCar(car1));
		assertFalse(model.addCar(car1));
		assertFalse(model.addCar(car1));
	}

	@Test
	public void delete() {
		assertTrue(model.addCar(car1));
		assertEquals(true, model.deleteCar(car1));
		assertEquals(false, model.deleteCar(car2));

	}



	@Test
	public void getAll() {
		model.addAll(cars);
		assertTrue(model.containsAll(cars));
		cars.add(car3);
		assertFalse(model.containsAll(cars));
	}

	@Test
	public void getCar() {

	}
	
//	 @Test
//	 public void update() {
//	 assertTrue(model.addCar(car1));
//	 CarEntity testCar= new CarEntity(car1.getId(), car1.getModel(),
//	 car1.getProduction(), car1.getEngine(), car1.isAc());
//	 assertNotEquals(car1, model.update(testCar));
//	 }

	
}
