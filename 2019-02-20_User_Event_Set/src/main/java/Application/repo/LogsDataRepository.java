package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.LogsDataItem;

public interface LogsDataRepository extends JpaRepository<LogsDataItem, Integer> {
}
