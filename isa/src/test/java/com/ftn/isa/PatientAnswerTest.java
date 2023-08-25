package com.ftn.isa;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.PessimisticLockingFailureException;

import com.ftn.isa.dto.QueryAnswerDTO;
import com.ftn.isa.model.PatientAnswer;
import com.ftn.isa.model.PatientQuestion;
import com.ftn.isa.repository.PatientAnswerRepository;
import com.ftn.isa.service.PatientAnswerService;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PatientAnswerTest {

    @Autowired
    private PatientAnswerService patientAnswerService;

    private List<QueryAnswerDTO> getTestCases() {
        PatientQuestion question1 = new PatientQuestion();
        question1.setId(1L);
        question1.setDescription("Have you ever donated blood or blood components voluntarily?");

        PatientQuestion question2 = new PatientQuestion();
        question2.setId(2L);
        question2.setDescription("Have you ever been rejected as a blood or blood component donor?");

        PatientQuestion question3 = new PatientQuestion();
        question3.setId(3L);
        question3.setDescription("Are you currently taking any medication?");

        PatientQuestion question4 = new PatientQuestion();
        question4.setId(4L);
        question4.setDescription("Do you have any allergies?");

        PatientQuestion question5 = new PatientQuestion();
        question5.setId(5L);
        question5.setDescription("Have you traveled to any foreign countries in the past 30 days?");

        QueryAnswerDTO answerDTO1 = new QueryAnswerDTO();
        answerDTO1.setUserId(3L);
        answerDTO1.setQuestion(question1);
        answerDTO1.setAnswer(false);

        QueryAnswerDTO answerDTO2 = new QueryAnswerDTO();
        answerDTO2.setUserId(3L);
        answerDTO2.setQuestion(question2);
        answerDTO2.setAnswer(false);

        QueryAnswerDTO answerDTO3 = new QueryAnswerDTO();
        answerDTO3.setUserId(3L);
        answerDTO3.setQuestion(question3);
        answerDTO3.setAnswer(true);

        QueryAnswerDTO answerDTO4 = new QueryAnswerDTO();
        answerDTO4.setUserId(3L);
        answerDTO4.setQuestion(question4);
        answerDTO4.setAnswer(false);

        QueryAnswerDTO answerDTO5 = new QueryAnswerDTO();
        answerDTO5.setUserId(3L);
        answerDTO5.setQuestion(question5);
        answerDTO5.setAnswer(false);

        List<QueryAnswerDTO> answerDTOs = new ArrayList<>();
        answerDTOs.add(answerDTO1);
        answerDTOs.add(answerDTO2);
        answerDTOs.add(answerDTO3);
        answerDTOs.add(answerDTO4);
        answerDTOs.add(answerDTO5);

        return answerDTOs;
    }

    @Test
    public void testSaveMultipleAnswers_Success() throws InterruptedException {
        List<QueryAnswerDTO> answerDTOs = getTestCases();

        List<PatientAnswer> savedAnswers = patientAnswerService.saveMultiple(answerDTOs);

        assertEquals(answerDTOs.size(), savedAnswers.size());
    }

    @Test
    public void testSaveMultipleAnswers_Exception() {
        List<QueryAnswerDTO> answerDTOs = getTestCases();
        answerDTOs.add(null);

        assertThrows(NullPointerException.class, () -> {
            patientAnswerService.saveMultiple(answerDTOs);
        });
    }

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Throwable {
        List<QueryAnswerDTO> answerDTOs = getTestCases();
        patientAnswerService.saveMultiple(answerDTOs);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            patientAnswerService.saveMultiple(answerDTOs);
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                patientAnswerService.checkIfPatientHasAlreadyAnswered(3L);
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
