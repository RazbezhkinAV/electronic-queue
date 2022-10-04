package ru.razbezhkin.electronicqueue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.razbezhkin.electronicqueue.domain.Visitor;

import java.util.Optional;

public interface VisitorRepository extends CrudRepository<Visitor,Long> {
    Optional<Visitor> getUserByLogin(String login);
}
