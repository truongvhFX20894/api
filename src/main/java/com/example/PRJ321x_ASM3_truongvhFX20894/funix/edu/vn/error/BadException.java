package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error;

public class BadException extends RuntimeException {
    public BadException(String message) {
        super(message);
    }

    public BadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadException(Throwable cause) {
        super(cause);
    }
}
