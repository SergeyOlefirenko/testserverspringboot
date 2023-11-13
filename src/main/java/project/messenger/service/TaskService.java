package project.messenger.service;
import project.messenger.service.dto.TaskDto;
import java.util.List;

public interface TaskService {
    List<TaskDto> findAll ();
    TaskDto findById(Long id);
    TaskDto save (TaskDto book);
    void deleteById (Long id);
}
