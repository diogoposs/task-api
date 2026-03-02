package taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}