package com.example.todo.dto;

import org.springframework.beans.BeanUtils;

import com.example.todo.entity.TaskEntity;
import com.example.todo.enums.StatusEnum;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String name;

    private String description;

    private StatusEnum status;

    public TaskDto(TaskEntity taskEntity) {
        BeanUtils.copyProperties(taskEntity, this);
    }

}
