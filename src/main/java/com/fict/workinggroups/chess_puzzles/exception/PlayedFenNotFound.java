package com.fict.workinggroups.chess_puzzles.exception;

public class PlayedFenNotFound extends RuntimeException {

    public PlayedFenNotFound() {
        super("The requested PlayedFen is not Found");
    }
}
