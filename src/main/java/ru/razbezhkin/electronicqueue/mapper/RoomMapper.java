package ru.razbezhkin.electronicqueue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.razbezhkin.electronicqueue.domain.Room;
import ru.razbezhkin.electronicqueue.domain.RoomDto;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDto toRoomDto(Room room);
}
