package com.ftn.isa.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserScheduleAppointmentDTO {

    private Long userId;
    private Long bloodCenterId;
    private LocalDate scheduleDate;
    private LocalTime startTime;
    private int duration;

    public UserScheduleAppointmentDTO() {
    }

    public UserScheduleAppointmentDTO(Long userId, Long bloodCenterId, LocalDate scheduleDate, LocalTime startTime,
            int duration) {
        super();
        this.userId = userId;
        this.bloodCenterId = bloodCenterId;
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Long getBloodCenterId() {
        return bloodCenterId;
    }

    public void setBloodCenterId(Long bloodCenterId) {
        this.bloodCenterId = bloodCenterId;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate date) {
        this.scheduleDate = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime time) {
        this.startTime = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int time) {
        this.duration = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

}
