package cryptobot.mws.controller;

import cryptobot.mws.domain.reponse.MessageResponse;
import cryptobot.mws.domain.reponse.TaskOutput;
import cryptobot.mws.domain.request.TaskInput;
import cryptobot.mws.service.api.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v0/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Set<TaskOutput>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/start")
    public ResponseEntity<MessageResponse> startTask(@RequestBody TaskInput taskIn) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.startTask(taskIn));
    }

    @PostMapping("/stop")
    public ResponseEntity<MessageResponse> stopTask(@RequestBody TaskInput taskIn) {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(taskService.stopTask(taskIn));
    }

}
