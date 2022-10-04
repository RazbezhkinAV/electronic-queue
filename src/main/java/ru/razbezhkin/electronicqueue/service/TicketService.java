package ru.razbezhkin.electronicqueue.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.*;
import ru.razbezhkin.electronicqueue.mapper.RoomMapper;
import ru.razbezhkin.electronicqueue.mapper.TicketMapper;
import ru.razbezhkin.electronicqueue.repository.TicketRepository;
import ru.razbezhkin.electronicqueue.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TimeTableService timeTableService;
    private final RoomService roomService;
    private final VisitorService visitorService;

    public TicketDto saveTicket(ReservationData reservationData) {
        Visitor visitor = visitorService.getUserByLogin(reservationData.userLogin());
        Room room = roomService.getRoomByName(reservationData.roomName());
        LocalTime reservedTime = DateUtils.toLocalTime(reservationData.time());

        if(visitor == null || room == null || timeTableService.isTimeInBorder(reservedTime)) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setCreated(LocalDateTime.now());
        ticket.setVisitor(visitor);
        ticket.setRoom(room);
        ticket.setTime(LocalDateTime.of(LocalDate.now(),reservedTime));

        return TicketMapper.INSTANCE.toTicketDto(ticketRepository.save(ticket));
    }

    public List<TicketDto> getTodayFreeTicketsByRoomName(String roomName) {

        Room room = roomService.getRoomByName(roomName);

        if (room == null) {
            return Collections.emptyList();
        }

        Set<LocalTime> busyTime = ticketRepository.findAllByRoom(room).stream()
                .map(Ticket::getTime)
                .map(LocalDateTime::toLocalTime)
                .collect(Collectors.toSet());

        List<LocalTime> defaultTimeTable = timeTableService.getTimeTable();

        return defaultTimeTable.stream()
                .filter(it -> !busyTime.contains(it))
                .map(it -> createTicketDto(room, it))
                .collect(Collectors.toList());

    }

    private TicketDto createTicketDto(Room room, LocalTime time) {
        return new TicketDto(
                LocalDateTime.of(LocalDate.now(), time),
                RoomMapper.INSTANCE.toRoomDto(room));
    }
}
