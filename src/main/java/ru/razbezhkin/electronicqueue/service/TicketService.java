package ru.razbezhkin.electronicqueue.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.Room;
import ru.razbezhkin.electronicqueue.domain.Ticket;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.mapper.TicketMapper;
import ru.razbezhkin.electronicqueue.provider.RoomsDataProvider;
import ru.razbezhkin.electronicqueue.provider.ScheduleDataProvider;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final ScheduleDataProvider scheduleService;
    private final RoomsDataProvider roomsDataProvider;

    public List<TicketDto> getAllFreeTickets() {
        List<LocalDateTime> todaySchedule = scheduleService.getTodaySchedule();

        return roomsDataProvider.getAllRooms().stream()
                .flatMap(it -> createTicketForRoom(it, todaySchedule).stream())
                .map(TicketMapper::toTicketDto)
                .toList();
    }

    private List<Ticket> createTicketForRoom(Room room, List<LocalDateTime> todaySchedule) {
        return todaySchedule.stream()
                .map(it -> new Ticket(it, room))
                .toList();
    }
}
