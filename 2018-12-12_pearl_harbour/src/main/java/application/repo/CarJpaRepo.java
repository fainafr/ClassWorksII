package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.Car;

/**
 * CRUDrepository vs JPArepository;
 * Spring automatically implements jdbc using <T,ID>; 
 * @author user
 *
 */
public interface CarJpaRepo extends JpaRepository<Car, Integer>{
	//TODO: extending methods; 
}
