package com.fict.workinggroups.chess_puzzles.exception;

public class WrongFenSolutionException extends RuntimeException {
    public WrongFenSolutionException() {
        super("Wrong fen solution! ");
    }
}
