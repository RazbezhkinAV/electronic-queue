package ru.razbezhkin.electronicqueue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.razbezhkin.electronicqueue.domain.Ticket;
import ru.razbezhkin.electronicqueue.domain.TicketDto;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    TicketDto toTicketDto(Ticket ticket);
}
