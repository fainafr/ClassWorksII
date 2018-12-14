package application.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entities.CarEntity;

public interface CarJpaRepository extends JpaRepository<CarEntity, Integer>{
	List<CarEntity> findByModel(String model);
	List<CarEntity> findByModelOrderByProduction(String model);
	List<CarEntity> findByEngineBetween(double engineFrom, double engineTo);
	
	@Query(value = "SELECT * FROM car_queries c WHERE c.model = ?1 AND c.engine BETWEEN ?2 AND ?3", 
		   nativeQuery = true)
	List<CarEntity> getBySomeQuery(String model, double engineMin, double engineMax);	
	
}
