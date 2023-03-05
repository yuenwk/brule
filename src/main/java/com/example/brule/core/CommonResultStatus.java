package com.example.brule.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonResultStatus implements IResultStatus {

	OK(200000, "成功"),

	FAIL(300000, "失败"),

	PARAM_ERROR(300002, "参数非法"),

	RECORD_NOT_EXIST(300003, "记录不存在"),

	UNAUTHORIZED(300004, "未授权，非法访问"),

	FORBIDDEN(300005, "禁止访问"),

	VALIDATE_FAILED(400000, "参数检验失败"),

	CONSTRAINT_VIOLATION(400001, "约束冲突"),

	BUSINESS_FAILED(499999, "业务处理异常"),

	SERVER_ERROR(500000, "服务器内部错误"),

	;

	private final long code;

	private final String message;

}
