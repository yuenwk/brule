package com.example.brule.sys.dto;

import com.example.brule.sys.domain.SysResource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResourceRequest(@NotBlank String name, @NotNull SysResource.Type type, @NotNull Long parentId,
		@NotBlank String permission, String icon, String url) {

}
