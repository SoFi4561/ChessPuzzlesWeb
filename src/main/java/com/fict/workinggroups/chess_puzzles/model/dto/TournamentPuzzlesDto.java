package com.fict.workinggroups.chess_puzzles.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TournamentPuzzlesDto {

    String id;
    String name;
    boolean tournamentActivated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date;

    Set<FenDto> puzzleList;

    int duration;

    public TournamentPuzzlesDto(String id, String name, boolean tournamentActivated, LocalDateTime date, Set<FenDto> puzzleList, int duration) {
        this.id = id;
        this.name = name;
        this.tournamentActivated = tournamentActivated;
        this.date = date;
        this.puzzleList = puzzleList;
        this.duration = duration;


    }

    public TournamentPuzzlesDto() {
    }


}
