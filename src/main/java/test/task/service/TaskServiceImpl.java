package test.task.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.task.model.Status;
import test.task.model.Task;
import test.task.model.User;
import test.task.repository.TaskRepository;
import test.task.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusService statusService;

    @Override
    public Task createTask(Task task, String email) {
        task.setAddedTime(LocalDateTime.now());
        task.setStatus(statusService.getStatusByName(task.getStatus().getStatusName()));
        task.setUser(userRepository.findByEmail(email));
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task taskFromDB = taskRepository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found Task with id - " + taskId));
        taskFromDB.setTitle(task.getTitle());
        taskFromDB.setDescription(task.getDescription());
        taskRepository.save(taskFromDB);
        return taskFromDB;
    }

    @Override
    public Task changeStatus(Long taskId, String status) {
        Task taskFromDB = taskRepository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found Task with id - " + taskId));
        taskFromDB.setStatus(statusService.getStatusByName(Status.of(status).getStatusName()));
        taskRepository.save(taskFromDB);
        return taskFromDB;
    }

    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAllByStatus(String status) {
        Sort byAddedTime = Sort.by("addedTime").descending();
        return taskRepository.findAllByStatus(statusService
                .getStatusByName(Status.of(status)
                        .getStatusName()), byAddedTime);
    }

    @Override
    public List<Task> findAll() {
        Sort byAddedTime = Sort.by("addedTime").descending();
        return taskRepository.findAll(byAddedTime);
    }

    @Override
    public Task changedUser(Long taskId, Long userId) {
        User userFromDB = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Not found User with id - " + userId));
        Task taskFromDB = taskRepository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found Task with id - " + taskId));
        taskFromDB.setUser(userFromDB);
        taskRepository.save(taskFromDB);
        return taskFromDB;
    }

    @Override
    public void add(Task task) {
        task.setAddedTime(LocalDateTime.now());
        taskRepository.save(task);
    }
}
