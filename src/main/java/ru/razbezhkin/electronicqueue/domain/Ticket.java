package ru.razbezhkin.electronicqueue.domain;

import java.time.LocalDateTime;

public record Ticket(LocalDateTime time, Room room) {
}
