package com.clv.jwat.note2p.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private boolean isDeleted;
    private boolean isDone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long categoryId;
}
