package org.example.football_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name cannot be blank")
    private String name;

    @NotNull(message = "Balance name cannot be null")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @DecimalMin(value = "0.0",  message = "Commission must be at least 0%")
    @DecimalMax(value = "10.0", message = "Commission must not exceed 10%")
    @NotNull(message = "Commission percentage cannot be blank")
    private Double commissionPercentage;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        player.setTeam(this);
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        player.setTeam(null);
    }

    public void increaseBalance(double increment) {
        this.balance += increment;
    }

    public void decreaseBalance(double decrement) {
        this.balance -= decrement;
    }
}