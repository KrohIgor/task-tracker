package test.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.task.model.User;
import test.task.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private static final int SIZE_PAGE = 10;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll(int page) {
        Pageable pageable = PageRequest.of(page, SIZE_PAGE);
        Page<User> pageUser = userRepository.findAll(pageable);
        return pageUser.get().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
