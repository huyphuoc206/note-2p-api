package com.clv.jwat.note2p.service;

import com.clv.jwat.note2p.entity.Task;

public interface ITaskService {

    Task addTask(Long categoryId, Task task);
    Task updateTask(Long id, Task task);
    boolean removeTask(Long id);
}
