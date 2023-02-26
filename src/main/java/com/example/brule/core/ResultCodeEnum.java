package com.example.brule.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCodeEnum implements IResult {

	SUCCESS(200000, "操作成功"),

	FAILED(300000, "操作失败"),

	VALIDATE_FAILED(400000, "参数检验失败"),

	UNAUTHORIZED(4010000, " 暂未登录或token已经过期"),

	VALIDATE_FAILED_NO_(4010001, "验证失败! "),

	VALIDATE_FAILED_TIMESTAMP_INVALID(4010005, "校验信息异常! 请求时间无效!"),

	VALIDATE_FAILED_DUPLICATION(4010006, "校验信息异常! 重复请求!"),

	VALIDATE_FAILED_SIGN(4010007, "校验信息异常! 参数校验失败!"),

	VALIDATE_FAILED_NO_MATCH(4010008, "校验信息异常! 无匹配信息!"),

	UNSUPPORTED_MEDIA_TYPE(415000, "不接受HTTP媒体类型"),

	FORBIDDEN(4030000, "没有相关权限"),

	BUSINESS_FAILED(499999, "业务处理异常"),

	UNKNOWN(500000, "服务器繁忙");

	private final long code;

	private final String message;

}
