package com.shinhan.heehee.exception;

public class BanUserException extends RuntimeException {
	
    public BanUserException() {
        super();
    }

    public BanUserException(String message) {
        super(message);
    }

    public BanUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public BanUserException(Throwable cause) {
        super(cause);
    }

}
