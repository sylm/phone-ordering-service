package com.bbiloskursky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalOrderException extends RuntimeException {

    public IllegalOrderException() {
    }

    public IllegalOrderException(Throwable cause) {
        super(cause);
    }

    public IllegalOrderException(String message) {
        super(message);
    }

    public IllegalOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
