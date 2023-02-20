package com.fict.workinggroups.chess_puzzles.exception;

public class LeaderBoardNotFoundException extends RuntimeException {

    public LeaderBoardNotFoundException() {
        super("Leaderboard not found  !");
    }
}
