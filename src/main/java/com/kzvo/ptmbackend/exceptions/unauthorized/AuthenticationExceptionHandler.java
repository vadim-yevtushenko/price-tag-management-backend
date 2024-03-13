package com.kzvo.ptmbackend.exceptions.unauthorized;

import com.kzvo.ptmbackend.exceptions.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class AuthenticationExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    protected ResponseEntity<Object> handleException(AuthenticationException ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

}