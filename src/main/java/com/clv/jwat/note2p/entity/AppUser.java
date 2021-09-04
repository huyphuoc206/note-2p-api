package com.clv.jwat.note2p.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AppUser {
    private Long id;
    private String email;
    private String password;
    private String fullname;
    private Timestamp createdAt;
    private boolean isActive;
}
