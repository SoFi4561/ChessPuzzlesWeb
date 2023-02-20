package com.fict.workinggroups.chess_puzzles.exception;

public class TournamentNotFound extends RuntimeException {

    public TournamentNotFound() {

        super("The requested Tournament is not Found");
    }
}
