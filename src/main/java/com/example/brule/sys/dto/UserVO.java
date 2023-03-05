package com.example.brule.sys.dto;

import com.example.brule.sys.domain.SysUser;

import java.time.LocalDateTime;

public record UserVO(Long id, String username, String fullName, String avatar, SysUser.Gender gender,
		SysUser.State state, LocalDateTime createdTime) {

	public UserVO(SysUser user) {
		this(user.getId(), user.getUsername(), user.getFullName(), user.getAvatar(), user.getGender(), user.getState(),
				user.getCreatedTime());
	}

}
