package ru.mif.fortunewheel.security;

import org.slf4j.Logger;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String format, Object... objects) {
        this(String.format(format, objects));
    }

    public AuthenticationException(String message, Logger logger) {
        this(message);
        logger.warn("Service Exception with message {}", message);
    }

    public AuthenticationException(Logger logger, String format, Object... objects) {
        this(String.format(format, objects), logger);
    }
}
