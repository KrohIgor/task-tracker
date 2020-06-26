package test.task.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.task.model.User;
import test.task.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByEmail(userName);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(userName);
            userBuilder.password(user.getPassword());
            userBuilder.roles("User");
        } else {
            throw new UsernameNotFoundException("User's name not found!");
        }
        return userBuilder.build();
    }
}
