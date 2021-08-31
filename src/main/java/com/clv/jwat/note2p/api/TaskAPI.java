package com.clv.jwat.note2p.api;

import com.clv.jwat.note2p.entity.Task;
import com.clv.jwat.note2p.service.ITaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class TaskAPI {

    private final ITaskService taskService;

    @PostMapping("/category/{categoryId}/tasks")
    public ResponseEntity<Task> addTask(@PathVariable Long categoryId, @RequestBody Task task) {
        Task result = taskService.addTask(categoryId, task);
        if(result != null) {
            log.info("Insert task {} success", task.getId());
            return ResponseEntity.ok(result);
        } else {
            log.error("Insert task {} fail", task.getName());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task result = taskService.updateTask(id, task);
        if(result != null) {
            log.info("Update task {} success", id);
            return ResponseEntity.ok(result);
        } else {
            log.error("Update task {} fail", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Boolean> removeTask(@PathVariable Long id) {
        boolean result = taskService.removeTask(id);
        if (result) {
            log.info("Remove task {} success", id);
            return ResponseEntity.ok(true);
        } else {
            log.error("Remove task {} fail", id);
            return ResponseEntity.badRequest().body(false);
        }
    }
}
