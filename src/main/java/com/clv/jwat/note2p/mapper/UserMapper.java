package com.clv.jwat.note2p.mapper;

import com.clv.jwat.note2p.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    AppUser findByEmailAndStatus(String email, @Param("active") boolean isActive);
    AppUser findByEmail(String email);
    AppUser findById(Long id, @Param("active") boolean isActive);
    void save(AppUser appUser);
}
