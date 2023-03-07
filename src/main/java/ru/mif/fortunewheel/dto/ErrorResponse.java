package ru.mif.fortunewheel.dto;

import java.time.ZonedDateTime;

public final class ErrorResponse {

    private final String message;
    private final String path;
    private final ZonedDateTime thrownAt;

    public ErrorResponse(String message, String path, ZonedDateTime thrownAt) {
        this.message = message;
        this.path = path;
        this.thrownAt = thrownAt;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public ZonedDateTime getThrownAt() {
        return thrownAt;
    }
}
