package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.LeaderBoardNotFoundException;
import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;
import com.fict.workinggroups.chess_puzzles.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {


    @Autowired
    LeaderboardService leaderboardService;


    @GetMapping("/list/{tournamentId}")
    public ResponseEntity getLeaderboard(@PathVariable String tournamentId) {

        try {
            Set<Leaderboard> leaderboard = this.leaderboardService.getLeaderboardByTournamentId(tournamentId);
            if (!leaderboard.isEmpty()) {
                return ResponseEntity.ok().body(leaderboard);
            } else {
                Set<Leaderboard> leaderboards = new HashSet<>();
                return ResponseEntity.status(422).body(leaderboards);
            }
        } catch (LeaderBoardNotFoundException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }

    }


}