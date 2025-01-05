package com.krowfeather.mediumelm.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.activity.entity.Activity;
import com.krowfeather.mediumelm.activity.entity.ActivityRegistration;
import com.krowfeather.mediumelm.activity.mapper.ActivityMapper;
import com.krowfeather.mediumelm.activity.mapper.ActivityRegistrationMapper;
import com.krowfeather.mediumelm.activity.service.ActivityRegistrationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ActivityRegistrationServiceImpl extends ServiceImpl<ActivityRegistrationMapper, ActivityRegistration> implements ActivityRegistrationService {
    private final ActivityRegistrationMapper activityRegistrationMapper;
    private final ActivityMapper activityMapper;
    public ActivityRegistrationServiceImpl(ActivityRegistrationMapper activityRegistrationMapper, ActivityMapper activityMapper) {
        this.activityRegistrationMapper = activityRegistrationMapper;
        this.activityMapper = activityMapper;
    }


    @Override
    public List<Activity> getActivityByMid(String mid) {
        List<ActivityRegistration> activityRegistrationList =  activityRegistrationMapper.selectList(new QueryWrapper<ActivityRegistration>().eq("mid", mid));
        List<Activity> activityList = new ArrayList<>();
        for(ActivityRegistration activityRegistration : activityRegistrationList){
            activityList.add(activityMapper.selectById(activityRegistration.getAid()));
        }
        activityList.sort(Comparator.comparingInt(Activity::getPriority).reversed());
        return activityList;
    }
}
