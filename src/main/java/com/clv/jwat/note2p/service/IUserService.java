package com.clv.jwat.note2p.service;

import com.clv.jwat.note2p.entity.AppUser;

public interface IUserService {
    AppUser findById(Long id, boolean isActive);

    AppUser register(AppUser appUser);

    AppUser checkLogin(String email, String password);
}
