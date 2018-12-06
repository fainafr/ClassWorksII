package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.Person;

public interface PersonJpaRepository extends JpaRepository<Person,String>{}
