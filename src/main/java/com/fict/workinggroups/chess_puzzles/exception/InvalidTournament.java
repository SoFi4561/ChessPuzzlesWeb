package com.fict.workinggroups.chess_puzzles.exception;

public class InvalidTournament extends RuntimeException {

    public InvalidTournament(String name) {

        super(String.format("Invalid Tournament, %s already exits", name));
    }
}