package ru.razbezhkin.electronicqueue.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.razbezhkin.electronicqueue.provider.ScheduleDataProvider;

import java.time.LocalDateTime;
import java.util.List;

class ScheduleDataProviderTest {

    @Test
    public void shouldTodaySchedule(){
        ScheduleDataProvider scheduleService = new ScheduleDataProvider();

        List<LocalDateTime> todaySchedule = scheduleService.getTodaySchedule();

        Assertions.assertThat(todaySchedule)
                .isNotEmpty()
                .size()
                .isEqualTo(10);

    }
}