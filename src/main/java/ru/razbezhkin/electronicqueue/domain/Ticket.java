package ru.razbezhkin.electronicqueue.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
public class Ticket {
    private final UUID traceId;
    private final LocalTime time;
}
