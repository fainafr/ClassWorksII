package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.entities.Person;
import application.entities.PesonCompositeID;

@Repository
public interface IPersonsJpaRepo extends JpaRepository<Person, PesonCompositeID> {

}
