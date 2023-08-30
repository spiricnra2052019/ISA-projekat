package com.ftn.isa;

import com.ftn.isa.dto.UserVisitHistoryDTO;
import com.ftn.isa.service.UserVisitHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StartAppointmentsOneBloodCenterAdminTest {

    @Autowired
    private UserVisitHistoryService userVisitHistoryService;

    @Test
    public void startAppointmentsWithSameDoctor() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                UserVisitHistoryDTO historyDto = UserVisitHistoryDTO.builder()
                        .description("Opis")
                        .bloodType("A")
                        .numberOfEquipments(3)
                        .quantity(10)
                        .scheduleCalendarId(2L)
                        .patientId(4L)
                        .build();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assertThrows(PessimisticLockingFailureException.class, () -> userVisitHistoryService.addReport(historyDto));
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                UserVisitHistoryDTO historyDto = UserVisitHistoryDTO.builder()
                        .description("Opis2")
                        .bloodType("B")
                        .numberOfEquipments(4)
                        .quantity(8)
                        .scheduleCalendarId(3L)
                        .patientId(3L)
                        .build();
                userVisitHistoryService.addReport(historyDto);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
