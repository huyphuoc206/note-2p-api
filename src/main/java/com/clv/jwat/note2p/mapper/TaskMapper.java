package com.clv.jwat.note2p.mapper;

import com.clv.jwat.note2p.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    Task findById(Long id);
    int update(Task task);
    void save(Task task);
}
