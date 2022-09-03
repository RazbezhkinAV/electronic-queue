package ru.razbezhkin.electronicqueue.provider;

import org.springframework.stereotype.Component;
import ru.razbezhkin.electronicqueue.domain.Room;

import java.util.List;

@Component
public class RoomsDataProvider {

    public List<Room> getAllRooms() {
        return List.of(
                new Room("room-217"),
                new Room("room-210"),
                new Room("room-313"),
                new Room("room-514")
        );
    }
}
