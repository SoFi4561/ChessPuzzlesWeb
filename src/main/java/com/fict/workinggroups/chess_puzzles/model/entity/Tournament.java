package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tournament {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private boolean tournamentActive;

    private int duration;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tournament_fen",
            joinColumns = {@JoinColumn(name = "tournament_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fen_id", referencedColumnName = "id")})
    private Set<Fen> fens = new HashSet<>();

    public Tournament() {
    }


    public Tournament(String name, boolean tournamentActive, LocalDateTime tournamentDate, int duration) {

        this.name = name;
        this.date = tournamentDate;
        this.tournamentActive = tournamentActive;
        this.duration = duration;


    }


}