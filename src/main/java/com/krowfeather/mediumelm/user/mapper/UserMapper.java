package com.krowfeather.mediumelm.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.krowfeather.mediumelm.user.entity.dto.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
