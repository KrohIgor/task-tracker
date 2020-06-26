package test.task.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.model.Status;
import test.task.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status add(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status getStatusByName(Status.StatusName statusName) {
        return statusRepository.findByStatusName(statusName);
    }

    @Override
    public Optional<Status> findById(Long statusId) {
        return statusRepository.findById(statusId);
    }
}
