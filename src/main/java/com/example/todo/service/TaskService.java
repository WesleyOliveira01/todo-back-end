package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.TaskDto;
import com.example.todo.enums.StatusEnum;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getById(Long id);

    TaskDto Create(TaskDto taskDto);

    TaskDto Update(Long id, TaskDto taskDto);

    void Delete(Long id);

    TaskDto updateStatus(Long id, StatusEnum status);
}
