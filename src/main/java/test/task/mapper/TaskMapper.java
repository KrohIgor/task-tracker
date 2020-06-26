package test.task.mapper;

import org.springframework.stereotype.Component;
import test.task.model.Status;
import test.task.model.Task;
import test.task.model.dto.TaskRequestDto;
import test.task.model.dto.TaskRequestEditDto;
import test.task.model.dto.TaskResponseDto;

@Component
public class TaskMapper {
    public Task getTaskFromTaskRequestDto(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(Status.of(taskRequestDto.getStatusName()));
        return task;
    }

    public TaskResponseDto getTaskResponseDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setTaskId(task.getTaskId());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus());
        taskResponseDto.setAddedTime(task.getAddedTime());
        taskResponseDto.setUser(task.getUser());
        return taskResponseDto;
    }

    public Task getTaskFromTaskRequestEditDto(TaskRequestEditDto taskRequestEditDto) {
        Task task = new Task();
        task.setTitle(taskRequestEditDto.getTitle());
        task.setDescription(taskRequestEditDto.getDescription());
        return task;
    }
}
