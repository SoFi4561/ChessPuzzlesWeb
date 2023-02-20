package com.fict.workinggroups.chess_puzzles.repository;


import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FenRepository extends JpaRepository<Fen, String> {


    Optional<Fen> findById(String id);

    //List<Fen> findBySolution(String solution);
    //List<Fen> findBySolutionIsNull();
}


