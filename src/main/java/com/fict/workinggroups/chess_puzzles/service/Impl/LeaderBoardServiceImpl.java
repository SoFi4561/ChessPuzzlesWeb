package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;
import com.fict.workinggroups.chess_puzzles.repository.LeaderboardRepository;
import com.fict.workinggroups.chess_puzzles.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class LeaderBoardServiceImpl implements LeaderboardService {

    @Autowired
    LeaderboardRepository leaderboardRepository;

    @Override
    public Set<Leaderboard> getLeaderboardByTournamentId(String id) {
        return this.leaderboardRepository.findAllByTournamentId(id);

    }

    @Override
    public List<Leaderboard> getAllPlayers() {
        return leaderboardRepository.findAll();
    }

}
