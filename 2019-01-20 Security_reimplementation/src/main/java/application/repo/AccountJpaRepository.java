package application.repo;

import org.springframework.data.repository.CrudRepository;

import application.entities.Account;

public interface AccountJpaRepository extends CrudRepository<Account, String>{

}
