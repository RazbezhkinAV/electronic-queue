package ru.razbezhkin.electronicqueue.mapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ru.razbezhkin.electronicqueue.domain.Ticket;
import ru.razbezhkin.electronicqueue.domain.TicketDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TicketMapper {

    public static TicketDto toTicketDto(Ticket ticket) {
        return new TicketDto(ticket.time(), ticket.room().roomName());
    }
}
