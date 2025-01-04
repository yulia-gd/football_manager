package org.example.football_manager.repository;

import org.example.football_manager.model.Player;
import org.example.football_manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t LEFT JOIN FETCH t.players")
    List<Team> findAll();
}
