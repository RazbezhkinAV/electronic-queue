package ru.razbezhkin.electronicqueue.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.razbezhkin.electronicqueue.domain.Room;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll();
    Room findByRoomName(String roomName);
}
