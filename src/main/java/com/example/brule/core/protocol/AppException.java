package com.example.brule.core.protocol;

import lombok.Getter;

public class AppException extends RuntimeException {

    @Getter
    private final long code;

    public AppException(String message) {
        this(CommonResultStatus.BUSINESS_FAILED.getCode(), message);
    }

    public AppException(long code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(IResultStatus status) {
        this(status.getCode(), status.getMessage());
    }

}
