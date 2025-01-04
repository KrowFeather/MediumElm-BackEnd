package com.krowfeather.mediumelm.common.config;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.user.entity.vo.AuthorizeVO;
import com.krowfeather.mediumelm.common.filter.JwtAuthorizerFilter;
import com.krowfeather.mediumelm.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import com.krowfeather.mediumelm.common.utils.JwtUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final JwtAuthorizerFilter jwtAuthorizerFilter;
    private final UserService userService;
    public SecurityConfig(JwtUtils jwtUtils, JwtAuthorizerFilter jwtAuthorizerFilter, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.jwtAuthorizerFilter = jwtAuthorizerFilter;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginProcessingUrl("/auth/login")
                        .failureHandler(this::onAuthenticationFailure)
                        .successHandler(this::onAuthenticationSuccess)
                )
                .logout((logout) -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling((handling) -> handling
                        .authenticationEntryPoint(this::onUnauthorized)
                        .accessDeniedHandler(this::onAccessDenied)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizerFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void onAccessDenied(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("Application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(Result.forbidden(e.getMessage()).toJson());
    }

    private void onUnauthorized(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("Application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(Result.unauthorized(e.getMessage()).toJson());
    }

    private void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String authorization = httpServletRequest.getHeader("Authorization");
        if (jwtUtils.invalidateJwt(authorization)) {
            writer.write(Result.success().toJson());
        } else {
            writer.write(Result.error("Logout failed").toJson());
        }
    }


    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("Application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        User user = (User) authentication.getPrincipal();
        String token = jwtUtils.createJwt(user, 1, user.getUsername());
        com.krowfeather.mediumelm.user.entity.dto.User account = userService.findByUsername(user.getUsername());
        AuthorizeVO authorizeVO = new AuthorizeVO();
        authorizeVO.setUsername(account.getUsername());
        authorizeVO.setRole(account.getRole());
        authorizeVO.setToken(token);
        authorizeVO.setExpires(jwtUtils.expireTime());
        httpServletResponse.getWriter().write(Result.success(authorizeVO).toJson());
    }

    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException {
        httpServletResponse.setContentType("Application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(Result.error("Wrong username or password").toJson());
    }
}
