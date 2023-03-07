package ru.mif.fortunewheel.security;

import org.slf4j.Logger;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String format, Object... objects) {
        this(String.format(format, objects));
    }

    public ForbiddenException(String message, Logger logger) {
        this(message);
        logger.warn("Service Exception with message {}", message);
    }

    public ForbiddenException(Logger logger, String format, Object... objects) {
        this(String.format(format, objects), logger);
    }
}
