package application.repo;

import org.springframework.data.repository.CrudRepository;

import application.entities.Role;

public interface RoleJpaRepository extends CrudRepository<Role,String>{

}
