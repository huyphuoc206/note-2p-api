package com.clv.jwat.note2p.service.impl;

import com.clv.jwat.note2p.entity.Category;
import com.clv.jwat.note2p.entity.Task;
import com.clv.jwat.note2p.mapper.TaskMapper;
import com.clv.jwat.note2p.service.ITaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService implements ITaskService {

    private final TaskMapper taskMapper;

    @Override
    public Task addTask(Long categoryId, Task task) {
        Category category = categoryMapper.findById(categoryId);
        if (category == null) return null;
        task.setCategoryId(categoryId);
        task.setDone(false);
        task.setDeleted(false);
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        taskMapper.save(task);
        if (task.getId() == null) return null;
        return task;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task oldTask = taskMapper.findById(id);
        if (oldTask == null) return null;
        task.setId(id);
        task.setCreatedAt(oldTask.getCreatedAt());
        task.setDeleted(oldTask.isDeleted());
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result = taskMapper.update(task);
        return result > 0 ? task : null;
    }

    @Override
    public boolean removeTask(Long id) {
        Task task = taskMapper.findById(id);
        if (task == null) return false;
        task.setDeleted(true);
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result = taskMapper.update(task);
        return result > 0;
    }
}
