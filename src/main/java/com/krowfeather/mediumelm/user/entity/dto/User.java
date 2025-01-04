package com.krowfeather.mediumelm.user.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.krowfeather.mediumelm.user.entity.BaseData;

import java.util.Date;

@Data
@TableName("user")
@AllArgsConstructor
public class User implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField("username")
    String username;
    @TableField("password")
    String password;
    @TableField("email")
    String email;
    @TableField("role")
    String role;
    @TableField("register_time")
    Date registerTime;
}
