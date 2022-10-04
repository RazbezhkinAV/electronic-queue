package ru.razbezhkin.electronicqueue.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisitorDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
