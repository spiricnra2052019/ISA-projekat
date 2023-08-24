package com.ftn.isa.mapper;

import com.ftn.isa.dto.RegisterdUserDTO;
import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.UserVisitHistory;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUserMapper {

    public static RegisterdUserDTO toDto(UserVisitHistory history) {
        return RegisterdUserDTO.builder()
                .id(history.getUser().getId())
                .name(history.getUser().getName())
                .lastname(history.getUser().getLastname())
                .date(history.getAppointment().getDate())
                .build();
    }

    public static List<RegisterdUserDTO> toDtoList(List<UserVisitHistory> histories) {
        List<RegisterdUserDTO> dtoList = new ArrayList<>();
        for (UserVisitHistory history : histories) {
            dtoList.add(toDto(history));
        }
        return dtoList.stream()
                .distinct()
                .toList();
    }
}
