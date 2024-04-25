package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TaskDto;
import com.example.todo.entity.TaskEntity;
import com.example.todo.enums.StatusEnum;
import com.example.todo.repository.TaskRepository;

@Service
public class ImplementTaskService implements TaskService {

    @Autowired
    private TaskRepository repository;

    public List<TaskDto> getAll() {
        List<TaskEntity> tasks = repository.findAll();
        if (tasks.isEmpty())
            throw new Error("Tasks not found");
        return tasks.stream().map(TaskDto::new).toList();
    }

    public TaskDto getById(Long id) {
        var existTask = repository.findById(id);
        if (!existTask.isPresent())
            throw new Error("Task not found");
        return existTask.map(TaskDto::new).get();
    }

    public TaskDto Create(TaskDto taskDto) {
        TaskEntity task = new TaskEntity(taskDto);
        repository.save(task);
        return new TaskDto(task);
    }

    public List<TaskDto> CreateMany(List<TaskDto> taskDtos) {
        ArrayList<TaskEntity> tasks = new ArrayList<>();
        for (TaskDto task : taskDtos) {
            tasks.add(new TaskEntity(task));
        }
        repository.saveAll(tasks);
        return tasks.stream().map(TaskDto::new).toList();
    }

    public TaskDto Update(Long id, TaskDto taskDto) {
        TaskEntity task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        repository.save(task);

        return new TaskDto(task);
    }

    public void Delete(Long id) {
        TaskEntity task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        repository.delete(task);
    }

    @Override
    public TaskDto updateStatus(Long id, StatusEnum status) {
        TaskEntity task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        repository.save(task);
        return new TaskDto(task);
    }

}
