package com.example.brule.sys.controller;

import com.example.brule.core.protocol.AppException;
import com.example.brule.core.protocol.CommonResult;
import com.example.brule.core.protocol.CommonResultStatus;
import com.example.brule.sys.domain.SysUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

//    @Value("${auth.cache_prefix:login_token:}")
//    private String cachePrefix;

    final UserDetailsService userDetailsService;

//    RememberMeServices rememberMeServices;

    @PostMapping("/login")
    public CommonResult<Map<String, Object>> login(@Valid @RequestBody LoginRequest param, HttpServletRequest request,
        HttpServletResponse response) {
        try {
            request.login(param.username, param.password);
        } catch (ServletException e) {
            throw new AppException(CommonResultStatus.BUSINESS_FAILED.getCode(), e.getMessage());
        }

        var auth = (Authentication) request.getUserPrincipal();
        var user = (SysUser) auth.getPrincipal();

        // rememberMeServices.loginSuccess(request, response, auth);

        return CommonResult.ok(Map.of("id", user.getId(), "fullName", user.getFullName()));
    }

    @PostMapping("/logout")
    public CommonResult<Void> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return CommonResult.ok();
    }

    @GetMapping("/current-user")
    public CommonResult<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal SysUser user) {
        return CommonResult.ok(Map.of("id", user.getId(), "fullName", user.getFullName()));
    }

    @GetMapping("/csrf")
    public CommonResult<String> csrf(HttpServletRequest request) {
        var csrf = (CsrfToken) request.getAttribute("_csrf");
        return CommonResult.ok(csrf.getToken());
    }

    record LoginRequest(@NotBlank String username, @NotBlank @Length(min = 6) String password) {

    }

}
