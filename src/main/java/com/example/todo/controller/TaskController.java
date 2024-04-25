package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.TaskDto;
import com.example.todo.enums.StatusEnum;
import com.example.todo.service.ImplementTaskService;

@RestController
@RequestMapping(value = "/v1/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    ImplementTaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        try {
            List<TaskDto> tasks = service.getAll();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        try {
            TaskDto task = service.getById(id);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        try {
            TaskDto task = service.Create(taskDto);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/insertMany")
    public ResponseEntity<List<TaskDto>> createMany(@RequestBody List<TaskDto> tasksDtos) {
        try {
            List<TaskDto> tasks = service.CreateMany(tasksDtos);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        try {
            TaskDto task = service.Update(id, taskDto);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long id, @RequestBody StatusEnum status) {
        try {
            TaskDto task = service.updateStatus(id, status);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        try {
            service.Delete(id);
            return ResponseEntity.ok("Task deleted with sucess");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
