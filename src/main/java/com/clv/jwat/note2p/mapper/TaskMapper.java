package com.clv.jwat.note2p.mapper;

import com.clv.jwat.note2p.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    Task findById(Long id);
    int update(Task task);
    void save(Task task);

    List<Task> findByCategoryId(Long id);
}
