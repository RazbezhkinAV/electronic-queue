package ru.razbezhkin.electronicqueue.domain;

public record User(Long id,
                   String firstName,
                   String lastName,
                   String email,
                   String phoneNumber) {
}
