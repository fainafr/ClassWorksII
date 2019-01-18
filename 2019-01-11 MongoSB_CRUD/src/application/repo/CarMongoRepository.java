package application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entities.Car;

public interface CarMongoRepository extends MongoRepository<Car,Integer> {

}
