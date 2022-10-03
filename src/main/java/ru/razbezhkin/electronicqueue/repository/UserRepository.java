package ru.razbezhkin.electronicqueue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.razbezhkin.electronicqueue.domain.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User getUserByFirstName(String firstName);
}
