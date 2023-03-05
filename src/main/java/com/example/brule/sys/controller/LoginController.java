package com.example.brule.sys.controller;

import com.example.brule.core.AppException;
import com.example.brule.core.CommonResultStatus;
import com.example.brule.sys.domain.SysUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

	UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<CurrentUser> login(@Valid @RequestBody LoginRequest param, HttpServletRequest request) {
		try {
			request.login(param.username, param.password);
		}
		catch (ServletException e) {
			throw new AppException(CommonResultStatus.BUSINESS_FAILED.getCode(), e.getMessage());
		}

		var auth = (Authentication) request.getUserPrincipal();
		var user = (SysUser) auth.getPrincipal();

		return ResponseEntity.ok(new CurrentUser(user.getId(), user.getUsername()));
	}

	@PostMapping("/logout")
	public LogoutResponse logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return new LogoutResponse();
	}

	// @GetMapping("/current-user")
	// public CurrentUser getCurrentUser(@AuthenticationPrincipal SysUser user) {
	// return new CurrentUser(user.getId(), user.getUsername());
	// }

	@GetMapping("/csrf")
	public CsrfResponse csrf(HttpServletRequest request) {
		var csrf = (CsrfToken) request.getAttribute("_csrf");
		return new CsrfResponse(csrf.getToken());
	}

	record LoginRequest(@NotBlank String username, @NotBlank @Length(min = 6) String password) {
	}

	record CurrentUser(Long id, String username) {
	}

	public record LogoutResponse() {
	}

	public record CsrfResponse(String token) {
	}

}
