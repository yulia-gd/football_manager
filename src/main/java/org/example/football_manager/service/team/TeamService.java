package org.example.football_manager.service.team;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.exception.InsufficientBalanceException;
import org.example.football_manager.exception.PlayerAlreadyInTeam;
import org.example.football_manager.exception.ResourceNotFoundException;
import org.example.football_manager.model.Player;
import org.example.football_manager.model.Team;
import org.example.football_manager.repository.TeamRepository;
import org.example.football_manager.request.TeamUpdateRequest;
import org.example.football_manager.service.player.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;
    private final PlayerService playerService;

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Team not found"));
    }

    @Override
    public Team updateTeam(TeamUpdateRequest team, Long id) {
        Team teamToUpdate = getTeam(id);
        updateExistedTeam(team, teamToUpdate);
        return teamRepository.save(teamToUpdate);
    }

    private void updateExistedTeam(TeamUpdateRequest team, Team existingTeam) {
        if (team.getName()!=null && !team.getName().isEmpty()) {
            existingTeam.setName(team.getName());
        }
        if (team.getBalance() !=null) {
            existingTeam.setBalance(team.getBalance());
        }
        if (team.getCommissionPercentage()!=null) {
            existingTeam.setCommissionPercentage(team.getCommissionPercentage());
        }
    }

    @Override
    public void deleteTeam(Long id) {
        Team teamToDelete = getTeam(id);
        playerService.detachPlayersFromTeam(teamToDelete);
        teamRepository.delete(teamToDelete);
    }


    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team addPlayerWithoutTeam(Long playerId, Long teamId) {
        Player playerToAdd = playerService.getPlayer(playerId);

        if (playerToAdd.getTeam() != null) {
            throw new PlayerAlreadyInTeam("Player already has a team");
        }

        Team team = getTeam(teamId);
        team.addPlayer(playerToAdd);
        teamRepository.save(team);

        return team;
    }

    @Override
    @Transactional
    public Team transferPlayerToTeam(Long playerId, Long teamId) {
        Player playerToAdd = playerService.getPlayer(playerId);
        Team team = getTeam(teamId);
        if(playerToAdd.getTeam() == null){
            throw new ResourceNotFoundException("Player don`t have a team");
        }
        if(playerToAdd.getTeam().equals(team)){
            throw new PlayerAlreadyInTeam("Player is already in this team");
        }
        transferPlayer(playerToAdd, team);

        return team;
    }


    protected void transferPlayer(Player player, Team toTeam){
        Team fromTeam = player.getTeam();
        if(fromTeam == null){
            throw new ResourceNotFoundException("Player don`t have a team");
        }
        double totalTransferCost =calculateTransferCost(fromTeam, player);
        System.out.println(totalTransferCost);
        if (toTeam.getBalance() < totalTransferCost) {
            throw new InsufficientBalanceException("There is not enough balance in the buying team's account. Team need "+totalTransferCost+", but have "+toTeam.getBalance());
        }

        toTeam.decreaseBalance(totalTransferCost);
        fromTeam.increaseBalance(totalTransferCost);

        fromTeam.removePlayer(player);
        toTeam.addPlayer(player);

        teamRepository.save(fromTeam);
        teamRepository.save(toTeam);
    }

    private double calculateTransferCost(Team fromTeam, Player player){
        double transferValue = player.getMonthsOfExperience() * 100000.0 / player.getAge();
        double commission = transferValue * fromTeam.getCommissionPercentage() / 100;
        return transferValue + commission;
    }
}
