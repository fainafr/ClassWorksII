package repo;

import org.springframework.data.repository.CrudRepository;

import entities.Role;

public interface RoleJpaRepository extends CrudRepository<Role,String>{

}
