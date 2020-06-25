package test.task.security;

import test.task.model.User;

public interface AuthenticationService {
    User register(String firstName, String lastName, String email, String password);
}
