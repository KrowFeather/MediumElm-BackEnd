package com.krowfeather.mediumelm.user.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    private String username;
    private String role;
    private String token;
    private Date expires;
}
