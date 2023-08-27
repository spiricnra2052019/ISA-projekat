package com.ftn.isa.dto;

import com.ftn.isa.model.RegisteredUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisterdUserDTO {
    Long id;

    String name;

    String lastname;

    String username;

    LocalDate date;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof RegisterdUserDTO)) {
            return false;
        }

        RegisterdUserDTO user = (RegisterdUserDTO) obj;

        return user.getId() == getId();
    }
}
