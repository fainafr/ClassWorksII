package repo;

import org.springframework.data.repository.CrudRepository;

import entities.Account;

public interface AccountJpaRepository extends CrudRepository<Account, String>{

}
