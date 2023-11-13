package project.messenger.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.messenger.service.TaskService;
import project.messenger.service.dto.TaskDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

//    @MessageMapping("/tasks")
//    @SendTo("/topic/tasks")

    @GetMapping("/tasks")
    public List<TaskDto> allTasks() {
        return taskService.findAll();
    }

    @GetMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.findById(id));
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) throws URISyntaxException {
        TaskDto result = taskService.save(task);
        return ResponseEntity.created(new URI("/api/v1/tasks/" + result.getId()))
                .body(result);
    }

//    @MessageMapping("/updateTask")
//    @SendTo("/topic/tasks")

    @PutMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto task) {
        return ResponseEntity.ok().body(taskService.save(task));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
