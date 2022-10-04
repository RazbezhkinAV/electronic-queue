package ru.razbezhkin.electronicqueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.User;
import ru.razbezhkin.electronicqueue.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByFirstName(String firstName) {
        return userRepository.getUserByFirstName(firstName);
    }
}
