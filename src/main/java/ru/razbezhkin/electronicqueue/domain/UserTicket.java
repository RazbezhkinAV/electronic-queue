package ru.razbezhkin.electronicqueue.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@ToString
@Getter
@RequiredArgsConstructor
public class UserTicket {
    private final UUID traceId;
    private final LocalDateTime dateTime;
    private final User user;
}
