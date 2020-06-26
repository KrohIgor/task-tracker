package test.task.service;

import java.util.List;
import test.task.model.Task;

public interface TaskService {
    Task createTask(Task task, String email);

    Task updateTask(Long taskId, Task task);

    Task changeStatus(Long taskId, String status);

    void deleteById(Long taskId);

    List<Task> findAllByStatus(String status);

    List<Task> findAllNew();

    List<Task> findAllOld();

    Task changedUser(Long taskId, Long userId);

    void add(Task task);
}
