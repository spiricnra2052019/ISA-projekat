package com.ftn.isa.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisteredUserDTO {
    Long id;

    String name;

    String lastname;

    String username;

    LocalDate date;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof RegisteredUserDTO))
            return false;

        RegisteredUserDTO user = (RegisteredUserDTO) obj;
        return this.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
