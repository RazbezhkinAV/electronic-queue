package ru.razbezhkin.electronicqueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.razbezhkin.electronicqueue.domain.RoomDto;
import ru.razbezhkin.electronicqueue.domain.TicketDto;
import ru.razbezhkin.electronicqueue.service.RoomService;
import ru.razbezhkin.electronicqueue.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final TicketService ticketService;

    @GetMapping
    public List<RoomDto> getAllRooms () {
        return roomService.getAllRooms();
    }

    @GetMapping("name-{roomName}/today-free-tickets")
    public List<TicketDto> getTodayFreeTickets (@PathVariable("roomName") String roomName) {
        return ticketService.getTodayFreeTicketsByRoomName(roomName);
    }

}
