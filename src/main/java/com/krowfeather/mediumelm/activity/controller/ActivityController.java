package com.krowfeather.mediumelm.activity.controller;

import com.krowfeather.mediumelm.activity.entity.ActivityRegistration;
import com.krowfeather.mediumelm.activity.service.ActivityRegistrationService;
import com.krowfeather.mediumelm.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
@Tag(name = "活动相关接口", description = "用于获取活动信息的接口")
public class ActivityController {

    private final ActivityRegistrationService activityRegistrationService;

    public ActivityController(ActivityRegistrationService activityRegistrationService) {
        this.activityRegistrationService = activityRegistrationService;
    }

    /**
     * 根据会员ID获取所有注册的活动
     *
     * @param mid 会员ID
     * @return 返回包含活动列表的Result对象
     */
    @Operation(
            summary = "根据会员ID获取所有注册的活动",
            description = "根据会员ID查询该会员所有已注册的活动列表。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取活动列表"),
    })
    @GetMapping("/{mid}")
    public Result getAllActivitiesByMid(@PathVariable String mid) {
        return Result.success(this.activityRegistrationService.getActivityByMid(mid));
    }
}
