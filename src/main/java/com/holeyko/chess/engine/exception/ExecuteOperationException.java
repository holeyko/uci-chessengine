package com.holeyko.chess.engine.exception;

public class ExecuteOperationException extends RuntimeException {
    public ExecuteOperationException() {
    }

    public ExecuteOperationException(String message) {
        super(message);
    }
}
