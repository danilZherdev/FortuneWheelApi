package ru.mif.fortunewheel.service;

import org.slf4j.Logger;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object... objects) {
        this(String.format(format, objects));
    }

    public ServiceException(String message, Logger logger) {
        this(message);
        logger.warn("Service Exception with message {}", message);
    }

    public ServiceException(Logger logger, String format, Object... objects) {
        this(String.format(format, objects), logger);
    }
}
