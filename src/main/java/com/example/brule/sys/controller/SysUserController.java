package com.example.brule.sys.controller;

import com.example.brule.sys.dto.UserVO;
import com.example.brule.sys.service.SysUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	final SysUserService userService;

	public SysUserController(SysUserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserVO>> list() {
        return ResponseEntity.ok(userService.list());
    }

}
