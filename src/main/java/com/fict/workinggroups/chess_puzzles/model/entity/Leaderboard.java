package com.fict.workinggroups.chess_puzzles.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Leaderboard {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    String nickname;

    Integer points;

    String tournamentId;

    Integer numberOfPlayedPuzzles;

    Integer numberOfCorrectPlayedPuzzles;

    Integer numberOfIncorrectPlayedPuzzles;

    public Leaderboard() {

    }


    public Leaderboard(String nickname, Integer points, String tournamentId, Integer numberOfPlayedPuzzles, Integer numberOfCorrectPlayedPuzzles, Integer numberOfIncorrectPlayedPuzzles) {

        this.nickname = nickname;
        this.points = points;
        this.tournamentId = tournamentId;
        this.numberOfPlayedPuzzles = numberOfPlayedPuzzles;
        this.numberOfCorrectPlayedPuzzles = numberOfCorrectPlayedPuzzles;
        this.numberOfIncorrectPlayedPuzzles = numberOfIncorrectPlayedPuzzles;
    }
}
