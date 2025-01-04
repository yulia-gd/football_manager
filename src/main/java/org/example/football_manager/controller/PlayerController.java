package org.example.football_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.dto.PlayerDto;
import org.example.football_manager.model.Player;
import org.example.football_manager.request.PlayerUpdateRequest;
import org.example.football_manager.response.ApiResponse;
import org.example.football_manager.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/player/{playerId}")
    public ResponseEntity<ApiResponse> getPlayerById(@PathVariable Long playerId) {
        Player player = playerService.getPlayer(playerId);
        PlayerDto playerDto = playerService.getPlayerDto(player);
        return  ResponseEntity.ok(new ApiResponse("Player found", playerDto));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        List<PlayerDto> playerDtos = playerService.getPlayerDtos(players);
        return ResponseEntity.ok(new ApiResponse("All players", playerDtos));
    }

    @PostMapping("/add/player")
    public ResponseEntity<ApiResponse> createPlayer(@RequestBody @Valid Player player) {
        System.out.println(player.getTeam());
        Player addedPlayer = playerService.addPlayer(player);
        PlayerDto playerDto = playerService.getPlayerDto(addedPlayer);
        return  ResponseEntity.status(CREATED).body(new ApiResponse("Player added", playerDto));
    }

    @PutMapping("/update/player/{playerId}")
    public ResponseEntity<ApiResponse> updatePlayer(@PathVariable Long playerId, @RequestBody @Valid PlayerUpdateRequest player) {
        Player updatedPlayer = playerService.updatePlayer(player, playerId );
        PlayerDto playerDto = playerService.getPlayerDto(updatedPlayer);
        return  ResponseEntity.ok(new ApiResponse("Player found", playerDto));
    }

    @DeleteMapping("/delete/player/{playerId}")
    public ResponseEntity<ApiResponse> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer( playerId );
        return  ResponseEntity.ok(new ApiResponse("Player deleted", null));
    }
}
