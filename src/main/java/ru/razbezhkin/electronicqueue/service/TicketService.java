package ru.razbezhkin.electronicqueue.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.User;
import ru.razbezhkin.electronicqueue.domain.UserTicket;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TicketService {

    private final List<UserTicket> busyTime = new ArrayList<>();

    public List<LocalTime> getInitialTimeForTicket() {
        List<LocalTime> times = new ArrayList<>();
        for (int i = 9; i <= 18; i++) {
            times.add(LocalTime.of(i, 0));
        }
        return times;
    }

    public void takeTime(User user, LocalDateTime dateTime) {
        UserTicket ticket = new UserTicket(
                UUID.randomUUID(),
                dateTime,
                user
        );

        busyTime.add(ticket);
        log.info(ticket.toString());
    }
}
