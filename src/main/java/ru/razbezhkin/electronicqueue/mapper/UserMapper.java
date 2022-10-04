package ru.razbezhkin.electronicqueue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.razbezhkin.electronicqueue.domain.Visitor;
import ru.razbezhkin.electronicqueue.domain.VisitorDto;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    VisitorDto toUserDto(Visitor visitor);
    Visitor toUser(VisitorDto visitorDto);
}
