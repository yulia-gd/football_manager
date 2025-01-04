package org.example.football_manager.dto;
import lombok.Data;

@Data
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private Long teamId;
    private String teamName;
}
