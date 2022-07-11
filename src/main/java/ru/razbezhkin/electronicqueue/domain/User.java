package ru.razbezhkin.electronicqueue.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
}
