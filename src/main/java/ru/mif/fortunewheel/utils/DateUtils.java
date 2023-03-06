package ru.mif.fortunewheel.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {

    /**
     * Convert date with type {@link Date} to date in {@link ZonedDateTime}.
     * @param date in {@link Date}.
     * @return date in {@link ZonedDateTime}.
     */
    public static ZonedDateTime fromDate(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault());
    }
}
