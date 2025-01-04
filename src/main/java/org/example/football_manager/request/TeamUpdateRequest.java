package org.example.football_manager.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TeamUpdateRequest {

    private String name;

    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @DecimalMin(value = "0.0",  message = "Commission must be at least 0%")
    @DecimalMax(value = "10.0", message = "Commission must not exceed 10%")
    private Double commissionPercentage;
}
