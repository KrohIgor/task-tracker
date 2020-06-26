package test.task.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.mapper.TaskMapper;
import test.task.model.Task;
import test.task.model.dto.TaskRequestDto;
import test.task.model.dto.TaskRequestEditDto;
import test.task.model.dto.TaskResponseDto;
import test.task.service.TaskService;
import test.task.service.UserService;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto,
                                      Authentication authentication) {
        Task task = taskMapper.getTaskFromTaskRequestDto(taskRequestDto);
        return taskMapper.getTaskResponseDto(taskService.createTask(task,
                /*authentication.getName()*/ null));
    }

    @PutMapping("/edit/{taskId}")
    public TaskResponseDto editTask(@PathVariable("taskId") Long taskId,
                                      @RequestBody TaskRequestEditDto taskRequestEditDto) {
        return taskMapper.getTaskResponseDto(taskService.updateTask(taskId,
                taskMapper.getTaskFromTaskRequestEditDto(taskRequestEditDto)));
    }

    @PutMapping("/update-status/{taskId}")
    public TaskResponseDto updateStatus(@PathVariable("taskId") Long taskId,
                                        @RequestParam(value = "statusName") String statusName) {
        return taskMapper.getTaskResponseDto(taskService.changeStatus(taskId, statusName));
    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteById(@PathVariable("taskId") Long taskId) {
        taskService.deleteById(taskId);
    }

    @GetMapping("/allByStatus/{taskStatus}")
    public List<TaskResponseDto> findAllTasksByStatus(@PathVariable("taskStatus")
                                                              String statusName) {
        return taskService.findAllByStatus(statusName).stream()
                .map(t -> taskMapper.getTaskResponseDto(t))
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<TaskResponseDto> findAllTasks() {
        return taskService.findAll().stream()
                .map(t -> taskMapper.getTaskResponseDto(t))
                .collect(Collectors.toList());
    }

    @PutMapping("/update-user")
    public TaskResponseDto updateUser(@RequestParam(value = "taskId") Long taskId,
                                      @RequestParam(value = "userId") Long userId) {
        return taskMapper.getTaskResponseDto(taskService.changedUser(taskId, userId));
    }
}
