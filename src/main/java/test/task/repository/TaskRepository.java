package test.task.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import test.task.model.Status;
import test.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(Status status, Sort sort);
}
