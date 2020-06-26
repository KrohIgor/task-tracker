package test.task.model.dto;

public class TaskRequestDto {
    private String title;
    private String description;
    private String statusName;

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "TaskRequestDto{" + "title='" + title + '\''
                + ", description='" + description + '\'' + ", statusName='" + statusName + '\''
                + '}';
    }
}
