package test.task.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    @Enumerated(value = EnumType.STRING)
    private StatusName statusName;

    public Status() {
    }

    private Status(StatusName statusName) {
        this.statusName = statusName;
    }

    public static Status of(String roleName) {
        return new Status(StatusName.valueOf(roleName));
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public StatusName getStatusName() {
        return statusName;
    }

    public void setStatusName(StatusName statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals(statusId, status.statusId)
                && statusName == status.statusName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusName);
    }

    @Override
    public String toString() {
        return "Status{" + "statusId=" + statusId + ", statusName=" + statusName + '}';
    }

    public enum StatusName {
        View, In_Progress, Done
    }
}
