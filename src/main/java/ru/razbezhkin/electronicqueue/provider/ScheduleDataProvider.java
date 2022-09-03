package ru.razbezhkin.electronicqueue.provider;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDataProvider {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final LocalDateTime START_TIME = LocalDateTime.of(CURRENT_DATE, LocalTime.of(8, 0));
    private static final LocalDateTime END_TIME = LocalDateTime.of(CURRENT_DATE, LocalTime.of(17, 0));

    public List<LocalDateTime> getTodaySchedule(){
        List<LocalDateTime> schedules = new ArrayList<>();
        schedules.add(START_TIME);
        schedules.add(END_TIME);
        int delta = END_TIME.getHour() - START_TIME.getHour();
        for(int i = 1; i < delta; i++){
            schedules.add(START_TIME.plusHours(i));
        }
        return schedules;
    }
}
