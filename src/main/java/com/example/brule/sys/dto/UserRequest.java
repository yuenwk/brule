package com.example.brule.sys.dto;

import com.example.brule.sys.domain.SysUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotBlank String username, @NotBlank String fullName, @NotNull SysUser.Gender gender,
		@NotBlank String avatar) {

}
