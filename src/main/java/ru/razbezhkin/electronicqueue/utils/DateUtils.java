package ru.razbezhkin.electronicqueue.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static LocalTime toLocalTime(String time) {
        return Optional.ofNullable(time)
                .map(it -> LocalTime.parse(it,
                        new DateTimeFormatterBuilder()
                                .appendPattern("HH:mm")
                                .toFormatter(Locale.ENGLISH)))
                .orElse(null);
    }
}
