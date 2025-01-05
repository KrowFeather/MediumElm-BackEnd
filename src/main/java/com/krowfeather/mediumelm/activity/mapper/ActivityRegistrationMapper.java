package com.krowfeather.mediumelm.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krowfeather.mediumelm.activity.entity.Activity;
import com.krowfeather.mediumelm.activity.entity.ActivityRegistration;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityRegistrationMapper extends BaseMapper<ActivityRegistration> {

}
