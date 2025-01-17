package org.example.cinehub_backend.exception;

import lombok.Getter;

@Getter
public class CineHubException extends RuntimeException {
    private ErrorType errorType;

    public CineHubException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
