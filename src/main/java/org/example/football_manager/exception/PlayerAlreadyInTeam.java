package org.example.football_manager.exception;

public class PlayerAlreadyInTeam extends RuntimeException {
    public PlayerAlreadyInTeam(String message) {
        super(message);
    }
}
