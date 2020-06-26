package test.task.model.dto;

import java.time.LocalDateTime;
import test.task.model.Status;

public class TaskResponseDto {
    private Long taskId;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime addedTime;
    private UserResponseDto userResponseDto;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    @Override
    public String toString() {
        return "TaskResponseDto{" + "taskId=" + taskId + ", title='" + title + '\''
                + ", description='" + description + '\'' + ", status=" + status
                + ", addedTime=" + addedTime + ", user=" + userResponseDto + '}';
    }
}
