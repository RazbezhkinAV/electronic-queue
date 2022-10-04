package ru.razbezhkin.electronicqueue.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {

    public static final LocalTime START_TIME = LocalTime.of(8, 0);
    public static final LocalTime END_TIME = LocalTime.of(18, 0);

    public List<LocalTime> getTimeTable() {
        List<LocalTime> result = new ArrayList<>();
        LocalTime currentTime = START_TIME;
        while (!currentTime.equals(END_TIME)) {
            if (currentTime.isAfter(LocalTime.now())) {
                result.add(currentTime);
            }
            currentTime = currentTime.plusMinutes(30);
        }
        return result;
    }

    public boolean isTimeInBorder(LocalTime time) {
        return !START_TIME.isBefore(time) && !END_TIME.isAfter(time);
    }

}
