package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;

import java.util.List;
import java.util.Optional;

public interface PlayedFensService {

    Optional<PlayedFen> getPlayerFenById(String id);

    boolean checkSolution(PlayedFenDto playedFenDto);

    void updateLeaderboard(PlayedFenDto playedFenDto);

    List<String> findPlayersByTournament(String tournamentId);

    List<String> findTournamentByPlayer(String playerId);

    Optional<PlayedFen>savePlayedFen(PlayedFen playedFen);

    List<PlayedFen> getAllPlayedFens();

    boolean checkSolution(PlayedFen playedFen);

    void updateLeaderboard(PlayedFen playedFens);

}
