package test.task.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.task.model.User;
import test.task.model.dto.UserRequestDto;
import test.task.model.dto.UserRequestEditDto;
import test.task.model.dto.UserResponseDto;

class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    void getUserFromUserRequestDto_Ok() {
        User expected = new User();
        expected.setFirstName("Bob");
        expected.setLastName("Jonson");
        expected.setPassword("1234");
        expected.setEmail("bob@ukr.net");

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName("Bob");
        userRequestDto.setLastName("Jonson");
        userRequestDto.setPassword("1234");
        userRequestDto.setEmail("bob@ukr.net");
        User actual = userMapper.getUserFromUserRequestDto(userRequestDto);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserFromUserRequestEditDto_Ok() {
        User expected = new User();
        expected.setFirstName("Bob");
        expected.setLastName("Jonson");
        expected.setEmail("bob@ukr.net");

        UserRequestEditDto userRequestEditDto = new UserRequestEditDto();
        userRequestEditDto.setFirstName("Bob");
        userRequestEditDto.setLastName("Jonson");
        userRequestEditDto.setEmail("bob@ukr.net");
        User actual = userMapper.getUserFromUserRequestEditDto(userRequestEditDto);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserResponseDto_Ok() {
        UserResponseDto expected = new UserResponseDto();
        expected.setId(1L);
        expected.setFirstName("Bob");
        expected.setLastName("Jonson");
        expected.setEmail("bob@ukr.net");

        User user = new User();
        user.setId(1L);
        user.setFirstName("Bob");
        user.setLastName("Jonson");
        user.setEmail("bob@ukr.net");
        user.setPassword("1234");
        UserResponseDto actual = userMapper.getUserResponseDto(user);

        Assertions.assertEquals(expected, actual);
    }
}
