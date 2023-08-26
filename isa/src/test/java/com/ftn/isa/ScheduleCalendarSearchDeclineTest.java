package com.ftn.isa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import com.ftn.isa.service.ScheduleCalendarService;

import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.dto.UserScheduleAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ScheduleCalendarSearchDeclineTest {

    @Autowired
    private ScheduleCalendarService scheduleCalendarService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        LocalDate startDate = LocalDate.now();
        LocalTime startTime = LocalTime.of(9, 0);
        int duration = 60;

        UserScheduleAppointmentDTO userScheduleAppointmentDTO1 = new UserScheduleAppointmentDTO();
        userScheduleAppointmentDTO1.setUserId(4L);
        userScheduleAppointmentDTO1.setBloodCenterId(1L);
        userScheduleAppointmentDTO1.setScheduleDate(startDate);
        userScheduleAppointmentDTO1.setStartTime(startTime);
        userScheduleAppointmentDTO1.setDuration(duration);
        scheduleCalendarService.createAppointmentByUser(userScheduleAppointmentDTO1);

        UserScheduleAppointmentDTO userScheduleAppointmentDTO2 = new UserScheduleAppointmentDTO();
        userScheduleAppointmentDTO2.setUserId(3L);
        userScheduleAppointmentDTO2.setBloodCenterId(1L);
        userScheduleAppointmentDTO2.setScheduleDate(startDate);
        userScheduleAppointmentDTO2.setStartTime(LocalTime.of(11, 0));
        userScheduleAppointmentDTO2.setDuration(60);
        scheduleCalendarService.createAppointmentByUser(userScheduleAppointmentDTO2);

        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                scheduleCalendarService.freeBloodCenters(startDate.toString(), startTime.toString(), duration);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                scheduleCalendarService.declineAppointment(9L);
            });

            System.out.println("Thread 2 Finished");
        });

        try {
            future1.get();
        } catch (Throwable throwable) {
            throw throwable;
        }

        try {
            future2.get();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            executor.shutdown();
        }
    }
}
