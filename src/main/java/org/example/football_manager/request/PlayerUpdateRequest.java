package org.example.football_manager.request;

import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerUpdateRequest {

    private String firstName;

    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Past(message = "Debut date must be in the past")
    private LocalDate debutDate;
}
