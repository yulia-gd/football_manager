package org.example.football_manager.service.player;

import org.example.football_manager.dto.PlayerDto;
import org.example.football_manager.model.Player;
import org.example.football_manager.model.Team;
import org.example.football_manager.request.PlayerUpdateRequest;

import java.util.List;

public interface IPlayerService {
    Player addPlayer(Player player);
    Player getPlayer(Long id);
    Player updatePlayer(PlayerUpdateRequest player, Long id);
    void deletePlayer(Long id);
    List<Player> getAllPlayers();
    PlayerDto getPlayerDto(Player player);
    List<PlayerDto> getPlayerDtos(List<Player> players);
    void detachPlayersFromTeam(Team team);
}
