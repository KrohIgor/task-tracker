package test.task.service;

import java.util.List;
import java.util.Optional;
import test.task.model.User;

public interface UserService {
    User add(User user);

    List<User> findAll(int page);

    Optional<User> findById(Long userId);

    void deleteById(Long userId);

    User updateUser(Long userId, User user);

    User findByEmail(String email);
}
