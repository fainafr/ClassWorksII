package application.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

@Entity
@Table(name="persons_jdbc")
public class Person {
	
	@Id
	private String name;
	private int age;
	private double weight;
	private boolean married;
}
