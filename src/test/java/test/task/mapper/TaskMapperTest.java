package test.task.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.task.model.Status;
import test.task.model.Task;
import test.task.model.dto.TaskRequestDto;
import test.task.model.dto.TaskRequestEditDto;

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
}
