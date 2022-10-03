package ru.razbezhkin.electronicqueue.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.razbezhkin.electronicqueue.domain.Room;
import ru.razbezhkin.electronicqueue.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {
    List<Ticket> findAllByRoom(Room room);
}
