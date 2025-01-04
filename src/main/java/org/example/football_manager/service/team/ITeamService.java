package org.example.football_manager.service.team;

import org.example.football_manager.model.Team;
import org.example.football_manager.request.TeamUpdateRequest;

import java.util.List;

public interface ITeamService {
    Team addTeam(Team team);
    Team getTeam(Long id);
    Team updateTeam(TeamUpdateRequest team, Long id);
    void deleteTeam(Long id);
    List<Team> getAllTeams();
    Team addPlayerWithoutTeam(Long playerId, Long teamId);
    Team transferPlayerToTeam(Long playerId, Long teamId);
}
