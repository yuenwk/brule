package com.example.brule.sys.dto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record PageVO<T>(List<T> list, long total) {

	public <E> PageVO(Page<E> page, Function<E, T> func) {
		this(page.getContent().stream().map(func).toList(), page.getTotalElements());
	}

}
