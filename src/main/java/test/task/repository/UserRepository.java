package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
