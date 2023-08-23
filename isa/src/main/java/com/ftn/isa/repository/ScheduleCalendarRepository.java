package com.ftn.isa.repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.ftn.isa.model.ScheduleCalendar;

public interface ScheduleCalendarRepository extends JpaRepository<ScheduleCalendar, Long> {
        public List<ScheduleCalendar> findAllByBloodCenterId(Long id);

        public List<ScheduleCalendar> findAllByUserId(Long id);

        @Query("SELECT sc FROM ScheduleCalendar sc WHERE " +
                        "sc.scheduleDate = :date")
        public List<ScheduleCalendar> searchByScheduleDate(
                        @Param("date") LocalDate date);

        @Query("SELECT sc FROM ScheduleCalendar sc WHERE sc.id = :id")
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
        Optional<ScheduleCalendar> findByIdForUpdate(@Param("id") Long id);
}
