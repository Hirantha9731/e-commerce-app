package com.example.mobileaccessoriesbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type invalid argument exception.
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException {
    /**
     * Instantiates a new Invalid argument exception.
     */
    public InvalidArgumentException() {
        super();
    }

    /**
     * Instantiates a new Invalid argument exception.
     *
     * @param msg the msg
     */
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
