package test.task.service;

import java.util.Optional;
import test.task.model.Status;

public interface StatusService {
    Status add(Status status);

    Status getStatusByName(Status.StatusName statusName);

    Optional<Status> findById(Long statusId);
}
