package com.krowfeather.mediumelm.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krowfeather.mediumelm.user.entity.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {
    User findByUsername(String username);
}
