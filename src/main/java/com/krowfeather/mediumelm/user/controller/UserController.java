package com.krowfeather.mediumelm.user.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.user.entity.dto.User;
import com.krowfeather.mediumelm.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "用户管理接口", description = "用于获取和管理用户信息的接口")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 返回用户信息
     */
    @Operation(
            summary = "根据用户名获取用户信息",
            description = "根据提供的用户名获取该用户的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
            @ApiResponse(responseCode = "404", description = "未找到该用户")
    })
    @GetMapping("/api/users/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        return Result.success(service.findByUsername(username));
    }

    @PostMapping("/auth/register")
    public Result register(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        service.save(user);
        User user1 = service.findByUsername(user.getUsername());
        return Result.success(user1);
    }
}
