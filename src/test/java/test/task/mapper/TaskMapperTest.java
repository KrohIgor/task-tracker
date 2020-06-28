package test.task.mapper;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.task.model.Status;
import test.task.model.Task;
import test.task.model.User;
import test.task.model.dto.TaskRequestDto;
import test.task.model.dto.TaskRequestEditDto;
import test.task.model.dto.TaskResponseDto;
import test.task.model.dto.UserResponseDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void getTaskFromTaskRequestDto_Ok() {
        Task expected = new Task();
        expected.setTitle("Solve");
        expected.setDescription("Solve this task");
        expected.setStatus(Status.of("View"));

        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTitle("Solve");
        taskRequestDto.setDescription("Solve this task");
        taskRequestDto.setStatusName("View");

        Task actual = taskMapper.getTaskFromTaskRequestDto(taskRequestDto);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTaskFromTaskRequestEditDto_Ok() {
        Task expected = new Task();
        expected.setTitle("Solve");
        expected.setDescription("Solve this task");

        TaskRequestEditDto taskRequestEditDto = new TaskRequestEditDto();
        taskRequestEditDto.setTitle("Solve");
        taskRequestEditDto.setDescription("Solve this task");

        Task actual = taskMapper.getTaskFromTaskRequestEditDto(taskRequestEditDto);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTaskResponseDto_Ok() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("Bob");
        userResponseDto.setLastName("Jonson");
        userResponseDto.setEmail("bob@ukr.net");

        UserMapper userMapper = mock(UserMapper.class);
        when(userMapper.getUserResponseDto(any(User.class))).thenReturn(userResponseDto);
        taskMapper.userMapper = userMapper;

        TaskResponseDto expected = new TaskResponseDto();
        expected.setTaskId(1L);
        expected.setTitle("Solve");
        expected.setDescription("Solve this task");
        expected.setStatus(Status.of("View"));
        expected.setAddedTime(LocalDateTime.of(2020, 6, 27, 15, 1));
        expected.setUserResponseDto(userResponseDto);

        Task task = new Task();
        task.setTaskId(1L);
        task.setTitle("Solve");
        task.setDescription("Solve this task");
        task.setStatus(Status.of("View"));
        task.setAddedTime(LocalDateTime.of(2020, 6, 27, 15, 1));
        task.setUser(new User());

        TaskResponseDto actual = taskMapper.getTaskResponseDto(task);
        Assertions.assertEquals(expected, actual);
    }
}
