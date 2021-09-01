package com.clv.jwat.note2p.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class Category {
    private Long id;
    private String title;
    private boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long userId;

    private List<Task> tasks;
    //Dùng để show tiến độ category
    private Long allTasksIsDone;
    private Long allTasks;
}
