package com.example.pastebin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class PasteExpiredException extends RuntimeException {
    public PasteExpiredException(String message) {
        super(message);
    }
}
