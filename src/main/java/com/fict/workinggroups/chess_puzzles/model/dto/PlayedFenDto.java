package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class PlayedFenDto {

    String fenId;
    String playedSolution;
    String tournamentId;
    Integer actualPoints;
    String username;

    public PlayedFenDto(String fenId, String playedSolution, String tournamentId, Integer actualPoints, String username) {
        this.fenId = fenId;
        this.playedSolution = playedSolution;
        this.tournamentId = tournamentId;
        this.actualPoints = actualPoints;
        this.username=username;
    }

    public PlayedFenDto() {


    }
}
