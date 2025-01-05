package com.krowfeather.mediumelm.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.activity.entity.Activity;
import com.krowfeather.mediumelm.activity.mapper.ActivityMapper;
import com.krowfeather.mediumelm.activity.service.ActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }
}
