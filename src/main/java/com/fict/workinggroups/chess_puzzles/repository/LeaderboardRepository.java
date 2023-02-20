package com.fict.workinggroups.chess_puzzles.repository;

import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, String> {

    Set<Leaderboard> findAllByTournamentId(String tournamentId);


    Leaderboard findLeaderboardByNicknameAndTournamentId(String nickname, String tournamentId);


}
