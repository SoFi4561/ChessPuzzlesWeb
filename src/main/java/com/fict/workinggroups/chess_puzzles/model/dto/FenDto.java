package com.fict.workinggroups.chess_puzzles.model.dto;


import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Data;

@Data
public class FenDto {
    String id;
    String fen;
    String description;

    Integer maxPoints;
    Status status;


    public FenDto(String id, String fen, String description, Integer maxPoints, Status status) {
        this.fen = fen;
        this.description = description;
        this.maxPoints = maxPoints;
        this.id = id;

        this.status = status;
    }
}
