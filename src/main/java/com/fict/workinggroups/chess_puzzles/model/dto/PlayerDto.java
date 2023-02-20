package com.fict.workinggroups.chess_puzzles.model.dto;

import lombok.Data;

@Data
public class PlayerDto {

    String id;
    String username;

    public PlayerDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public PlayerDto() {

    }
}
