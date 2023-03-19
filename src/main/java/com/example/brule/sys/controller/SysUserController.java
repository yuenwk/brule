package com.example.brule.sys.controller;

import com.example.brule.sys.dto.UserRequest;
import com.example.brule.sys.dto.UserVO;
import com.example.brule.sys.service.SysUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	final SysUserService userService;

	@GetMapping
	public List<UserVO> list() {
		return userService.list();
	}

	@PostMapping
	public void create(@RequestBody @Valid UserRequest user) {
		userService.create(user);
	}

	@PutMapping("{id}")
	public void update(@RequestBody @Valid UserRequest user, @PathVariable Long id) {
		userService.update(id, user);
	}

}
