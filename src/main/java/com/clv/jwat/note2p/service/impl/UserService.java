package com.clv.jwat.note2p.service.impl;

import com.clv.jwat.note2p.entity.AppUser;
import com.clv.jwat.note2p.mapper.UserMapper;
import com.clv.jwat.note2p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userMapper.findByEmail(email, true);
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
}
