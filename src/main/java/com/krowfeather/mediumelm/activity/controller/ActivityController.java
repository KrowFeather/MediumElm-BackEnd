package com.krowfeather.mediumelm.activity.controller;

import com.krowfeather.mediumelm.activity.entity.Activity;
import com.krowfeather.mediumelm.activity.entity.ActivityRegistration;
import com.krowfeather.mediumelm.activity.service.ActivityRegistrationService;
import com.krowfeather.mediumelm.activity.service.ActivityService;
import com.krowfeather.mediumelm.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityRegistrationService activityRegistrationService;

    public ActivityController(ActivityRegistrationService activityRegistrationService) {
        this.activityRegistrationService = activityRegistrationService;
    }

    @GetMapping("/{mid}")
    public Result getAllActivitiesByMid(@PathVariable String mid){
        return Result.success(this.activityRegistrationService.getActivityByMid(mid));
    }
}
