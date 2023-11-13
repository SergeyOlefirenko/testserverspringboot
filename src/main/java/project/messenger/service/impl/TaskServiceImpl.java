package project.messenger.service.impl;

import project.messenger.model.Task;
import project.messenger.repository.TaskRepository;
import project.messenger.service.TaskService;
import project.messenger.service.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> findAll() {
        // Преобразование Task в TaskDto
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public TaskDto findById(Long id) {
        return Optional.ofNullable(taskRepository.findById(id))
                .map(optionalBook -> optionalBook.map(this::convertToDto).orElse(null))
                .orElse(null);
    }

    @Override
    @Transactional
    public TaskDto save(TaskDto taskDto) {
        // Преобразование TaskDto в Task
        Task savedTask = taskRepository.save(convertToEntity(taskDto));
        // Преобразование сохраненного Task в TaskDto
        return convertToDto(savedTask);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto convertToDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getTaskDate());
    }

    private Task convertToEntity(TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getDescription(), taskDto.getTaskDate());
    }
}