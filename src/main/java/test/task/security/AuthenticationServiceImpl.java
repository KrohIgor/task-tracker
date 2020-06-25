package test.task.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.task.model.User;
import test.task.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/
    @Autowired
    private UserService userService;
    @Override
    public User register(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
