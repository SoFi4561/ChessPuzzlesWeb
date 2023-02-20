package com.fict.workinggroups.chess_puzzles.exception;

public class PlayedFenAlreadyExistsException extends RuntimeException {

    public PlayedFenAlreadyExistsException() {
        super("The PlayedFen Already Exists");
    }
}
