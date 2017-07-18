package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {
    private DateUtil() {}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime convertToLocalDateTime(String formattedDateTime, String pattern) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(f.parse(formattedDateTime));
    }
}
