package com.fict.workinggroups.chess_puzzles.exception;

public class FenNotFound extends RuntimeException {

    public FenNotFound() {

        super("The requested FEN is not Found");
    }
}