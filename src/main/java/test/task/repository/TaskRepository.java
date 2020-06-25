package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
