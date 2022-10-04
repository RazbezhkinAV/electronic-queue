package ru.razbezhkin.electronicqueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.razbezhkin.electronicqueue.domain.Visitor;
import ru.razbezhkin.electronicqueue.domain.VisitorDto;
import ru.razbezhkin.electronicqueue.exception.UserException;
import ru.razbezhkin.electronicqueue.mapper.UserMapper;
import ru.razbezhkin.electronicqueue.repository.VisitorRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class VisitorService implements UserDetailsService {
    private final VisitorRepository visitorRepository;
    private final PasswordEncoder passwordEncoder;


    public Visitor getUserByLogin(String login) {
        return visitorRepository.getUserByLogin(login).orElseThrow(() -> new UserException("Login " + login + " not found!"));
    }

    public VisitorDto saveUser(VisitorDto visitorDto) {

        Visitor visitor = UserMapper.INSTANCE.toUser(visitorDto);
        visitor.setPassword(passwordEncoder.encode(visitor.getPassword()));

        return UserMapper.INSTANCE.toUserDto(
                visitorRepository.save(visitor)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Visitor visitor = getUserByLogin(username);
        if (visitor == null) {
            throw new UsernameNotFoundException("Person not found in database");
        }

        return new User(visitor.getLogin(), visitor.getPassword(), Collections.emptyList());
    }
}
