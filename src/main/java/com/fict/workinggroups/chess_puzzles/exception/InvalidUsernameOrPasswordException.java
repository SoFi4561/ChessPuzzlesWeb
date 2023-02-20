package com.fict.workinggroups.chess_puzzles.exception;

public class InvalidUsernameOrPasswordException extends RuntimeException {

    public InvalidUsernameOrPasswordException() {
        super("Username must start with letter");
    }

}
