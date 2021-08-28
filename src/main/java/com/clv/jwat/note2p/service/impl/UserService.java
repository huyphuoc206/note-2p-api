package com.clv.jwat.note2p.service.impl;

import com.clv.jwat.note2p.entity.AppUser;
import com.clv.jwat.note2p.mapper.UserMapper;
import com.clv.jwat.note2p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userMapper.findByEmailAndStatus(email, true);
        if (user == null) {
            log.error("User not found: {}", email);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found: {}", email);
            return user;
        }
    }

    @Override
    public AppUser findById(Long id, boolean isActive) {
        return userMapper.findById(id, isActive);
    }

    @Override
    public AppUser register(AppUser appUser) {
        AppUser user = userMapper.findByEmail(appUser.getEmail());
        if(user != null) return null;
        log.info("Saving new user {} to the database", appUser.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setActive(true);
        appUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userMapper.save(appUser);
        return appUser;
    }
}
