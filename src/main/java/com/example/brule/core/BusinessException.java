package com.example.brule.core;

public class BusinessException extends RuntimeException {

	private final long code;

	public BusinessException() {
		super();
		this.code = ResultCodeEnum.BUSINESS_FAILED.getCode();
	}

	public BusinessException(IResult resultCode, Object... params) {
		super(String.format(resultCode.getMessage(), params));
		this.code = resultCode.getCode();
	}

	public BusinessException(String message) {
		super(message);
		this.code = ResultCodeEnum.BUSINESS_FAILED.getCode();
	}

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

}
