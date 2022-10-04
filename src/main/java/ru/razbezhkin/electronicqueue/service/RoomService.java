package ru.razbezhkin.electronicqueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.Room;
import ru.razbezhkin.electronicqueue.domain.RoomDto;
import ru.razbezhkin.electronicqueue.mapper.RoomMapper;
import ru.razbezhkin.electronicqueue.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDto> getAllRooms () {
        return roomRepository.findAll().stream()
                .map(RoomMapper.INSTANCE::toRoomDto)
                .collect(Collectors.toList());
    }

    public Room getRoomByName (String name) {
        return roomRepository.findByRoomName(name);
    }
}
