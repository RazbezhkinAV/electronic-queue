package ru.razbezhkin.electronicqueue.domain;

public record ReservationData(String userLogin,
                              String time,
                              String roomName) {
}
