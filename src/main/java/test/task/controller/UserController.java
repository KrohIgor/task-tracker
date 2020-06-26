package test.task.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.mapper.UserMapper;
import test.task.model.dto.UserRequestEditDto;
import test.task.model.dto.UserResponseDto;
import test.task.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/all")
    public List<UserResponseDto> allUsers(@RequestParam(value = "numberPage") int page) {
        return userService.findAll(page).stream()
                .map(u -> userMapper.getUserResponseDto(u))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{usesId}")
    public UserResponseDto findById(@PathVariable("usesId") Long id) {
        return userMapper.getUserResponseDto(userService.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found User with id - " + id)));
    }

    @DeleteMapping("/delete/{usesId}")
    public void deleteById(@PathVariable("usesId") Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/update/{usesId}")
    public UserResponseDto updateUser(@PathVariable("usesId") Long id,
                                      @RequestBody UserRequestEditDto userRequestEditDto) {
        return userMapper.getUserResponseDto(userService.updateUser(id,
                userMapper.getUserFromUserRequestEditDto(userRequestEditDto)));
    }
}
