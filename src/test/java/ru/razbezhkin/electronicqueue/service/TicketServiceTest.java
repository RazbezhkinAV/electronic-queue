package ru.razbezhkin.electronicqueue.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.razbezhkin.electronicqueue.domain.Room;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.provider.RoomsDataProvider;
import ru.razbezhkin.electronicqueue.provider.ScheduleDataProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    ScheduleDataProvider scheduleService;
    @Mock
    RoomsDataProvider roomsDataProvider;

    TicketService ticketService;


    @BeforeEach
    void setUp() {
        ticketService = new TicketService(scheduleService, roomsDataProvider);

        Mockito.when(scheduleService.getTodaySchedule()).thenReturn(
                List.of(
                        LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                        LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)),
                        LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)),
                        LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))
                )
        );

        Mockito.when(roomsDataProvider.getAllRooms()).thenReturn(
                List.of(
                        new Room("room-217"),
                        new Room("room-222"),
                        new Room("room-223")
                )
        );
    }

    @Test
    public void test1() {

        List<TicketDto> allFreeTickets = ticketService.getAllFreeTickets();

        Assertions.assertThat(allFreeTickets)
                .isNotEmpty()
                .size()
                .isEqualTo(12);

        checkRoom(allFreeTickets,"room-217");
        checkRoom(allFreeTickets,"room-222");
        checkRoom(allFreeTickets,"room-223");
    }

    private void checkRoom(List<TicketDto> tickets, String roomName){
        List<TicketDto> ticketRoom217 = tickets.stream()
                .filter(it -> it.roomName().equals(roomName))
                .sorted(Comparator.comparing(TicketDto::time))
                .toList();

        Assertions.assertThat(ticketRoom217)
                .isNotEmpty()
                .size()
                .isEqualTo(4);

        Assertions.assertThat(ticketRoom217.get(0))
                .extracting(TicketDto::time)
                .isEqualTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));

        Assertions.assertThat(ticketRoom217.get(3))
                .extracting(TicketDto::time)
                .isEqualTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
    }
}