package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class LeaderboardDto {
    String id;

    String playerName;

    Integer points;

    public LeaderboardDto() {
    }

    public LeaderboardDto(String playerName, Integer points) {
        this.playerName = playerName;
        this.points = points;
    }
}
