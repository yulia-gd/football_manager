package org.example.football_manager.service.player;

import lombok.AllArgsConstructor;
import org.example.football_manager.dto.PlayerDto;
import org.example.football_manager.exception.ResourceNotFoundException;
import org.example.football_manager.model.Player;
import org.example.football_manager.model.Team;
import org.example.football_manager.repository.PlayerRepository;
import org.example.football_manager.request.PlayerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Player not found"));
    }

    @Override
    public Player updatePlayer(PlayerUpdateRequest player, Long id) {
        Player playerToUpdate = getPlayer(id);
        updateExistingPlayer(playerToUpdate, player);
        return playerRepository.save(playerToUpdate);
    }

    private void updateExistingPlayer(Player existingPlayer, PlayerUpdateRequest player) {
        if(player.getFirstName()!=null && !player.getFirstName().isEmpty()) {
            existingPlayer.setFirstName(player.getFirstName());
        }
        if (player.getLastName()!=null && !player.getLastName().isEmpty()) {
            existingPlayer.setLastName(player.getLastName());
        }
        if (player.getDateOfBirth()!=null) {
            existingPlayer.setDateOfBirth(player.getDateOfBirth());
        }
        if (player.getDebutDate()!=null) {
            existingPlayer.setDebutDate(player.getDebutDate());
        }
    }

    @Override
    public void deletePlayer(Long id) {
        Player playerToDelete = getPlayer(id);
        if(playerToDelete.getTeam()!=null)
            playerToDelete.getTeam().removePlayer(playerToDelete);
        playerRepository.delete(playerToDelete);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public PlayerDto getPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        playerDto.setExperience(player.getMonthsOfExperience());
        playerDto.setAge(player.getAge());
        if(player.getTeam()!=null) {
            playerDto.setTeamId(player.getTeam().getId());
            playerDto.setTeamName(player.getTeam().getName());
        }
        return playerDto;
    }

    @Override
    public List<PlayerDto> getPlayerDtos(List<Player> players) {
        return players.stream().map(this::getPlayerDto).toList();
    }

    @Override
    public void detachPlayersFromTeam(Team team) {
        for (Player player : team.getPlayers()) {
            player.setTeam(null);
            playerRepository.save(player);
        }
    }
}
