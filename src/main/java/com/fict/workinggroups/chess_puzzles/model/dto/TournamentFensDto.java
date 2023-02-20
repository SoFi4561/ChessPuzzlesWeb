package com.fict.workinggroups.chess_puzzles.model.dto;

import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import lombok.Data;

import java.util.List;

@Data
public class TournamentFensDto {

    List<Fen> fens;
    int duration;

    public TournamentFensDto(List<Fen> fens, int duration) {
        this.fens = fens;
        this.duration = duration;
    }
}
