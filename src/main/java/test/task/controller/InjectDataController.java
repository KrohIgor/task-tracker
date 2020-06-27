package test.task.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.model.Status;
import test.task.model.Task;
import test.task.model.User;
import test.task.service.StatusService;
import test.task.service.TaskService;
import test.task.service.UserService;

@RestController
@RequestMapping("/inject")
public class InjectDataController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostConstruct
    public void injectStatusesToDb() {
        statusService.add(Status.of("View"));
        statusService.add(Status.of("In_Progress"));
        statusService.add(Status.of("Done"));
    }

    @PostConstruct
    public void injectUserToDb() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Jonson");
        user.setEmail("bob@ukr.net");
        user.setPassword("1234");
        userService.add(user);
    }

    @PostMapping("/user")
    public String injectUsersToDB() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setFirstName("Bob" + i);
            user.setLastName("Jonson" + i);
            user.setEmail("bob" + i + "@ukr.net");
            user.setPassword("1234" + i);
            userService.add(user);
        }
        return "50 users successfully added to DB!";
    }

    @PostMapping("/task")
    public String injectTasksToDB() {
        for (int i = 0; i < 50; i++) {
            Task task = new Task();
            task.setTitle("Implement");
            task.setDescription("Implement the main model");
            task.setUser(userService.findById((long) (i + 1)).get());
            task.setStatus(statusService.findById((long) (i % 3) + 1).get());
            taskService.add(task);
        }
        return "50 tasks successfully added to DB!";
    }
}
