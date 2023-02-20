package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<Fen> getAllFens();

    List<Fen> getAllFensWithSolution();

    Optional<Fen> saveFen(String fen, String description, Integer maxPoints, String solution);

    Optional<Fen> getFenById(String id);

    String deleteFen(String id);

    Optional<Fen> edit(String id, String fen, String description, Integer maxPoints, String solution);

    Optional<Fen> findById(String id);

    boolean isValidFen(String fen);

    Optional<Fen> save(FenSolutionDto fenSolutionDto);

    Optional<Fen> edit(String id, FenSolutionDto fenSolutionDto);

    Optional<Fen> addFenSolution(String id, String solution);

    Optional<Fen> changeStatusToApproved(String id);

    Optional<Fen> changeStatusToDeclined(String id);
}
