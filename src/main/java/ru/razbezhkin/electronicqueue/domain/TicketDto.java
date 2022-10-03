package ru.razbezhkin.electronicqueue.domain;

import java.time.LocalDateTime;

public record TicketDto(LocalDateTime time, RoomDto room) {
}
