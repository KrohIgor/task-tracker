package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(Status.StatusName statusName);
}
