package com.ftn.isa.mapper;

import com.ftn.isa.dto.RegisteredUserDTO;
import com.ftn.isa.model.UserVisitHistory;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUserMapper {

    public static RegisteredUserDTO toDto(UserVisitHistory history) {
        return RegisteredUserDTO.builder()
                .id(history.getUser().getId())
                .name(history.getUser().getName())
                .lastname(history.getUser().getLastname())
                .username(history.getUser().getUsername())
                .date(history.getAppointment().getDate())
                .build();
    }

    public static List<RegisteredUserDTO> toDtoList(List<UserVisitHistory> histories) {
        List<RegisteredUserDTO> dtoList = new ArrayList<>();
        for (UserVisitHistory history : histories) {
            dtoList.add(toDto(history));
        }
        return dtoList.stream()
                .distinct()
                .toList();
    }
}
