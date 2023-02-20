package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;

import java.util.List;
import java.util.Set;

public interface LeaderboardService {

    Set<Leaderboard> getLeaderboardByTournamentId(String id);

    List<Leaderboard> getAllPlayers();
}
