package com.tangenta.exceptions;

public class BaseException extends RuntimeException {
    

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
