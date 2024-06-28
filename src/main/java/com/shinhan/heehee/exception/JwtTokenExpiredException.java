package com.shinhan.heehee.exception;

public class JwtTokenExpiredException extends RuntimeException{

	public JwtTokenExpiredException() {
        super();
    }

    public JwtTokenExpiredException(String message) {
        super(message);
    }

    public JwtTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenExpiredException(Throwable cause) {
        super(cause);
    }
}
