package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Fen {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "fen")
    private String fen;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "maxPoints")
    private Integer maxPoints;

    private String solution;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "fens")
    private Set<Tournament> tournaments;


    public Fen(String fen, String description, Integer maxPoints, String solution) {
        this.fen = fen;
        this.description = description;

        this.maxPoints = maxPoints;
        this.solution = solution;
        this.status = Status.PENDING;

    }

    public Fen(String id, String fen, String description, Integer maxPoints) {
        this.id = id;
        this.fen = fen;
        this.description = description;
        this.maxPoints = maxPoints;
        this.status = Status.PENDING;

    }

    public Fen() {

    }

}