package com.fict.workinggroups.chess_puzzles.exception;

public class PlayerNotFound extends RuntimeException {

    public PlayerNotFound() {

        super("The requested Player is not Found");
    }
}

