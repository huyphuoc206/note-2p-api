package com.clv.jwat.note2p.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Category {
    private Long id;
    private String title;
    private boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long userId;
}
