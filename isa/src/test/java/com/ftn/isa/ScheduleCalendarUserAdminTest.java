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
public class ScheduleCalendarUserAdminTest {

    @Autowired
    private ScheduleCalendarService scheduleCalendarService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ScheduleCalendarDTO scheduleDTO1 = new ScheduleCalendarDTO();
        scheduleDTO1.setBloodCenterId(1L);
        scheduleDTO1.setDate(LocalDate.now());
        scheduleDTO1.setStartTime(LocalTime.of(9, 0));
        scheduleDTO1.setDuration(60);

        UserScheduleAppointmentDTO userScheduleAppointmentDTO = new UserScheduleAppointmentDTO();
        userScheduleAppointmentDTO.setUserId(3L);
        userScheduleAppointmentDTO.setBloodCenterId(1L);
        userScheduleAppointmentDTO.setScheduleDate(LocalDate.now());
        userScheduleAppointmentDTO.setStartTime(LocalTime.of(9, 0));
        userScheduleAppointmentDTO.setDuration(60);

        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                scheduleCalendarService.save(scheduleDTO1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                scheduleCalendarService.createAppointmentByUser(userScheduleAppointmentDTO);
            });
            System.out.println("Thread 2 Finished");
        });

        try {
            future1.get(); // Raises ExecutionException for any exception from the first thread
        } catch (Throwable throwable) {
            // Re-throws the exception
            throw throwable;
        }

        try {
            future2.get(); // Raises ExecutionException for any exception from the second thread
        } catch (Throwable throwable) {
            // Re-throws the exception
            throw throwable;
        } finally {
            executor.shutdown();
        }
    }
}
