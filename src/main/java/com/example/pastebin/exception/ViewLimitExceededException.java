package com.example.pastebin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
    public class ViewLimitExceededException extends RuntimeException {
        public ViewLimitExceededException(String message) {
            super(message);
        }
    }


