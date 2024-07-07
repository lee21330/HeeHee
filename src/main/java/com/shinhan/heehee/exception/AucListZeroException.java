package com.shinhan.heehee.exception;

public class AucListZeroException extends RuntimeException {
	
    public AucListZeroException() {
        super();
    }

    public AucListZeroException(String message) {
        super(message);
    }

    public AucListZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public AucListZeroException(Throwable cause) {
        super(cause);
    }

}
