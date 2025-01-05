package com.krowfeather.mediumelm.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.krowfeather.mediumelm.user.entity.dto.User;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set money = money - #{total} where username = #{username}")
    void updateMoney(String username, Double total);
}
