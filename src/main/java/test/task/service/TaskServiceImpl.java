package test.task.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        task.setTaskId(taskId);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task changeStatus(Long taskId, String status) {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setStatus(Status.of(status));
        Task taskFromDB = taskRepository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found Task with id - " + taskId));
        task.setTitle(taskFromDB.getTitle());
        task.setDescription(taskFromDB.getDescription());
        task.setAddedTime(taskFromDB.getAddedTime());
        task.setUser(taskFromDB.getUser());
        return task;
    }

    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAllByStatus(String status) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        Sort byAddedTime = Sort.by("addedTime");
        return taskRepository.findAll(byAddedTime);
    }

    @Override
    public Task changedUser(Long taskId, Long userId) {
        Task task = new Task();
        task.setTaskId(taskId);
        User userFromDB = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Not found User with id - " + userId));
        task.setUser(userFromDB);
        Task taskFromDB = taskRepository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found Task with id - " + taskId));
        task.setStatus(taskFromDB.getStatus());
        task.setTitle(taskFromDB.getTitle());
        task.setDescription(taskFromDB.getDescription());
        task.setAddedTime(taskFromDB.getAddedTime());
        return task;
    }
}
