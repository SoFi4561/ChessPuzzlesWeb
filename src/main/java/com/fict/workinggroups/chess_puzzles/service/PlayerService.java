package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayerDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<Player> getPlayerById(String id);

    List<Player> getAllPlayers();

    Optional<Player> deletePlayer(String id);


    Optional<Player> editPlayer(String id, PlayerDto playerDto);

    boolean hasUserId(String playerId);

    Player savePlayer(String username);


}
