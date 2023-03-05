package com.example.brule.core;

import lombok.Getter;

public class AppException extends RuntimeException {

	@Getter
	private final long code;

	public AppException(String message) {
		this(400, message);
	}

	public AppException(long code, String message) {
		super(message);
		this.code = code;
	}

	public AppException(IResultStatus status) {
		this(status.getCode(), status.getMessage());
	}

}
