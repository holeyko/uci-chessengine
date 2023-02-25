package com.holeyko.chess.engine.exception;

public class UnknownOperationExceptoin extends RuntimeException {
    public UnknownOperationExceptoin() {
    }

    public UnknownOperationExceptoin(String message) {
        super(message);
    }
}
