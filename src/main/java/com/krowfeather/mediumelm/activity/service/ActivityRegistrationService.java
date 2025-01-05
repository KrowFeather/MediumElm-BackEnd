package com.krowfeather.mediumelm.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krowfeather.mediumelm.activity.entity.Activity;
import com.krowfeather.mediumelm.activity.entity.ActivityRegistration;

import java.util.List;

public interface ActivityRegistrationService extends IService<ActivityRegistration> {
    List<Activity> getActivityByMid(String mid);
}
