package com.example.brule.core.protocol;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handleDefaultErrorView(Exception ex, HttpServletRequest request) {
        if (ex instanceof AppException e) {
            return new CommonResult<>(e.getCode(), ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException e) {
            String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(i -> i.getField() + " " + i.getDefaultMessage())
                .distinct()
                .collect(Collectors.joining(","));

            return new CommonResult<>(CommonResultStatus.VALIDATE_FAILED.getCode(), message);
        } else if (ex instanceof ConstraintViolationException e) {
            String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

            return new CommonResult<>(CommonResultStatus.CONSTRAINT_VIOLATION.getCode(), message);
        }

        log.error("Handle exception, message={}, requestUrl={}", ex.getMessage(), request.getRequestURI(), ex);

        return new CommonResult<>(CommonResultStatus.SERVER_ERROR.getCode(), ex.getMessage());
    }

}