package org.example.football_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.model.Team;
import org.example.football_manager.request.TeamUpdateRequest;
import org.example.football_manager.response.ApiResponse;
import org.example.football_manager.service.team.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team/add")
    public ResponseEntity<ApiResponse> addTeam(@RequestBody @Valid Team team) {
        Team addedTeam = teamService.addTeam(team);
        return ResponseEntity.status(CREATED).body(new ApiResponse("Team added", addedTeam));
    }

    @GetMapping("team/{teamId}")
    public ResponseEntity<ApiResponse> getTeamById(@PathVariable Long teamId) {
        Team team = teamService.getTeam(teamId);
        return ResponseEntity.ok(new ApiResponse("Team found", team));
    }

    @PutMapping("/update/team/{teamId}")
    public ResponseEntity<ApiResponse> updateTeam(@PathVariable Long teamId, @RequestBody @Valid TeamUpdateRequest teamUpdateRequest) {
        Team updatedTeam = teamService.updateTeam(teamUpdateRequest, teamId);
        return ResponseEntity.ok(new ApiResponse("Team updated", updatedTeam));
    }

    @DeleteMapping("/delete/team/{teamId}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok(new ApiResponse("Team deleted", null));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(new ApiResponse("All teams", teams));
    }

    @PostMapping("add/player/{playerId}/team/{teamId}")
    public ResponseEntity<ApiResponse> addPlayerWithoutTeamToTeam(@PathVariable Long teamId, @PathVariable Long playerId) {
        Team team = teamService.addPlayerWithoutTeam(playerId, teamId);
        return ResponseEntity.ok(new ApiResponse("Player added to team", team));
    }

    @PatchMapping("/transfer/player/{playerId}/team/{teamId}")
    public ResponseEntity<ApiResponse> transferPlayerToTeam(@PathVariable Long playerId, @PathVariable Long teamId) {
        Team team = teamService.transferPlayerToTeam(playerId, teamId);
        return ResponseEntity.ok(new ApiResponse("Player transferred", team));
    }
}
