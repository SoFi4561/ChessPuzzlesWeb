package com.fict.workinggroups.chess_puzzles.model.dto;

import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Data;

@Data
public class FenSolutionDto {
    String fen;
    String description;

    Integer maxPoints;
    Status status;

    String solution;


    public FenSolutionDto() {
    }


}
