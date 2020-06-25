package test.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.mapper.UserMapper;
import test.task.model.dto.UserRequestDto;
import test.task.model.dto.UserResponseDto;
import test.task.security.AuthenticationService;

@RestController
@RequestMapping
public class RegistrationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.getUserResponseDto(authenticationService.register(
                userRequestDto.getFirstName(), userRequestDto.getLastName(),
                userRequestDto.getEmail(), userRequestDto.getPassword()));
    }
}
