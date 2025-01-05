package com.krowfeather.mediumelm.user.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        return Result.success(service.findByUsername(username));
    }
}
