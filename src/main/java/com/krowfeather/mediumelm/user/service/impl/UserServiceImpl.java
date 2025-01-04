package com.krowfeather.mediumelm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.user.entity.dto.User;
import com.krowfeather.mediumelm.user.mapper.UserMapper;
import com.krowfeather.mediumelm.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword()).authorities(user.getRole()).build();

    }

    @Override
    public User findByUsername(String username) {
        Wrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        return this.userMapper.selectOne(wrapper);
    }
}
