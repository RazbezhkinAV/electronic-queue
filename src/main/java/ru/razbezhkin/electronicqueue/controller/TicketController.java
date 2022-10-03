package ru.razbezhkin.electronicqueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.razbezhkin.electronicqueue.domain.ReservationData;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.service.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PutMapping
    public TicketDto reservationTicket(@RequestBody ReservationData reservationData){
        return ticketService.saveTicket(reservationData);
    }
}
