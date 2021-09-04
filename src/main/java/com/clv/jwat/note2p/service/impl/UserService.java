package com.clv.jwat.note2p.service.impl;

import com.clv.jwat.note2p.entity.AppUser;
import com.clv.jwat.note2p.mapper.UserMapper;
import com.clv.jwat.note2p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser checkLogin(String email, String password) {
        AppUser appUser = userMapper.findByEmailAndStatus(email, true);
        if(appUser == null) return null; // wrong email
        // must compare hash code password request and password in database, can not encode then compare
        if(passwordEncoder.matches(password, appUser.getPassword())) {
            return appUser;
        } else {
            return null;
        }
    }

    @Override
    public AppUser findById(Long id, boolean isActive) {
        return userMapper.findById(id, isActive);
    }

    @Override
    public AppUser register(AppUser appUser) {
        AppUser user = userMapper.findByEmail(appUser.getEmail());
        if (user != null) return null;
        log.info("Saving new user {} to the database", appUser.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        System.out.println(appUser.getPassword());
        appUser.setActive(true);
        appUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userMapper.save(appUser);
        return appUser;
    }
}
