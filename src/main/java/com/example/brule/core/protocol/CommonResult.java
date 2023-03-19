package com.example.brule.core.protocol;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CommonResult<T> {

    private LocalDateTime timestamp = LocalDateTime.now();

    private Long status;

    private String message;

    private T data;

    public CommonResult(Long status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResult(IResultStatus status) {
        this.status = status.getCode();
        this.message = status.getMessage();
    }

    public CommonResult(T data) {
        this.status = CommonResultStatus.OK.getCode();
        this.message = CommonResultStatus.OK.getMessage();
        this.data = data;
    }

    public static CommonResult<Void> ok() {
        return new CommonResult<>(CommonResultStatus.OK);
    }

    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<>(data);
    }

}
