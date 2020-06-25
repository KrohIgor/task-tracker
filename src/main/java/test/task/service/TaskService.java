package test.task.service;

import java.util.List;
import test.task.model.Task;

public interface TaskService {
    Task createTask(Task task);

    Task updateTask(Long taskId, Task task);

    Task changeStatus(Long taskId, String status);

    void deleteById(Long taskId);

    List<Task> findAllByStatus(String status);

    List<Task> findAll();

    Task changedUser(Long taskId, Long userId);
}
