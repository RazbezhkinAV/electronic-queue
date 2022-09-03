package ru.razbezhkin.electronicqueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("free")
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllFreeTickets();
    }
}
