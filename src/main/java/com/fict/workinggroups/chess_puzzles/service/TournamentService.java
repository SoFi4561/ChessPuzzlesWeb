package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.dto.TournamentDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TournamentService {

    Optional<Tournament> getTournamentById(String id);

    List<Tournament> getAllTournaments();

    Optional<Tournament> deleteTournament(String id);

    Optional<Tournament> saveTournament(TournamentDto tournamentDto);

//    Tournament addTournament(Tournament tournament);

    Set<Player> listPlayersInTournament(String tournamentId);

    Set<FenDto> listFensInTournament(String tournamentId);

    Set<FenDto> listFensByTournamentName(String name);

    Optional<Tournament> edit(String id, TournamentDto tournamentDto);

    Optional<Tournament> edit(String id, Tournament tournament);

    Optional<Tournament> save(Tournament tournament);

    Tournament findTournamentByName(String name);

}
