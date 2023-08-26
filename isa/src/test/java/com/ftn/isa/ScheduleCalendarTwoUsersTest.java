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
public class ScheduleCalendarTwoUsersTest {

    @Autowired
    private ScheduleCalendarService scheduleCalendarService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        UserScheduleAppointmentDTO userScheduleAppointmentDTO1 = new UserScheduleAppointmentDTO();
        userScheduleAppointmentDTO1.setUserId(4L);
        userScheduleAppointmentDTO1.setBloodCenterId(1L);
        userScheduleAppointmentDTO1.setScheduleDate(LocalDate.now());
        userScheduleAppointmentDTO1.setStartTime(LocalTime.of(9, 0));
        userScheduleAppointmentDTO1.setDuration(60);

        UserScheduleAppointmentDTO userScheduleAppointmentDTO2 = new UserScheduleAppointmentDTO();
        userScheduleAppointmentDTO2.setUserId(3L);
        userScheduleAppointmentDTO2.setBloodCenterId(1L);
        userScheduleAppointmentDTO2.setScheduleDate(LocalDate.now());
        userScheduleAppointmentDTO2.setStartTime(LocalTime.of(9, 0));
        userScheduleAppointmentDTO2.setDuration(60);

        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                scheduleCalendarService.createAppointmentByUser(userScheduleAppointmentDTO1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                scheduleCalendarService.createAppointmentByUser(userScheduleAppointmentDTO2);
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
