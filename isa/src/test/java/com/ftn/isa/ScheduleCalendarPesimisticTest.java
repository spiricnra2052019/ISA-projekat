package com.ftn.isa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.isa.service.ScheduleCalendarService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ScheduleCalendarPesimisticTest {

    @Autowired
    private ScheduleCalendarService scheduleCalendarService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            scheduleCalendarService.scheduleAppointmentForUser(4L, 3L);
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                scheduleCalendarService.scheduleAppointmentForUser(4L, 4L);
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
