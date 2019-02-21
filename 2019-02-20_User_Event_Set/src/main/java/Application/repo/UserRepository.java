package Application.repo;

import Application.entities.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface UserRepository extends JpaRepository<UserItem, Integer> {
   //public List<UserItem> searchByFilter(HashMap<String, String> filter);
   //TODO: https://stackoverflow.com/questions/19583540/spring-data-jpa-no-property-found-for-type-exception
}
