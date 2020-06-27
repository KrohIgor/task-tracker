package test.task.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.mapper.UserMapper;
import test.task.model.User;
import test.task.model.dto.AuthenticationRequestDto;
import test.task.model.dto.UserRequestDto;
import test.task.model.dto.UserResponseDto;
import test.task.security.AuthenticationService;
import test.task.security.jwt.JwtTokenProvider;
import test.task.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.getUserResponseDto(authenticationService.register(
                userRequestDto.getFirstName(), userRequestDto.getLastName(),
                userRequestDto.getEmail(), userRequestDto.getPassword()));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String userName = requestDto.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,
                    requestDto.getPassword()));
            User userFromDB = userService.findByEmail(userName);
            if (userFromDB == null) {
                throw new UsernameNotFoundException("Usr with name " + userName + " not found");
            }
            String token = jwtTokenProvider.createToken(userName);
            Map<Object, Object> response = new HashMap<>();
            response.put("userName", userName);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
