package application.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.Car;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
@ToString

@Entity
@Table(name="car_queries")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String model;
	private LocalDate production;
	private double engine;
	private boolean ac;
	
	public CarEntity(String model, LocalDate production, double engine, boolean ac) {
		this.model = model;
		this.production = production;
		this.engine = engine;
		this.ac = ac;
	}
	public CarEntity(Car car) {this(car.getModel(), car.getProductionDate(), car.getEngine(), car.isAc());}
	
	public Car getCar() {return new Car(model, production, engine, ac);}
	
}
