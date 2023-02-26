package com.example.brule.sys.dto;

import com.example.brule.sys.domain.SysRole;
import com.example.brule.sys.domain.SysUser;

import java.time.LocalDateTime;
import java.util.Set;

public record RoleUserVO(Long id, String username, String fullName, String avatar, SysUser.Gender gender,
		SysUser.State state, Set<SysRole> roles, LocalDateTime createdTime) {
}
