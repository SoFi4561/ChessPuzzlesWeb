package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.dto.TournamentDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;


    @Autowired
    private FenRepository fenRepository;

    @Override
    public Optional<Tournament> getTournamentById(String id) {
        if (tournamentRepository.findById(id).isPresent()) {
            return this.tournamentRepository.findById(id);
        } else {
            throw new TournamentNotFound();
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> deleteTournament(String id) {
        if (tournamentRepository.findById(id).isPresent()) {
            this.tournamentRepository.deleteById(id);
            return tournamentRepository.findById(id);
        } else {
            throw new TournamentNotFound();
        }

    }

    @Override
    public Optional<Tournament> edit(String id, TournamentDto tournamentDto) {
        Tournament tournament = this.tournamentRepository.findById(id).orElseThrow(TournamentNotFound::new);


        tournament.setName(tournamentDto.getName());
        tournament.setDate(tournamentDto.getDate());
        tournament.setTournamentActive(tournamentDto.isTournamentActivated());

        return Optional.of(this.tournamentRepository.save(tournament));

    }


    @Override
    public Optional<Tournament> saveTournament(TournamentDto tournamentDto) {
        if (this.tournamentRepository.findByName(tournamentDto.getName()).isPresent()) {
            throw new InvalidTournament(tournamentDto.getName());
        }
        Tournament tournament = new Tournament(tournamentDto.getName(), tournamentDto.isTournamentActivated(), tournamentDto.getDate(), tournamentDto.getDuration());
        List<Fen> fens = this.fenRepository.findAll();
        Set<Fen> fenSet = new HashSet<>(fens);

        tournament.setFens(fenSet);

        return Optional.of(this.tournamentRepository.save(tournament));


    }


    @Override
    public Set<Player> listPlayersInTournament(String tournamentId) {
//        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
//            if (this.tournamentRepository.findById(tournamentId).get().getPlayers().size() > 0)
//                return this.tournamentRepository.findById(tournamentId).get().getPlayers();
//        }
//        return this.tournamentRepository.findById(tournamentId).get().getPlayers();

        return null;
    }

    @Override
    public Set<FenDto> listFensInTournament(String tournamentId) {
        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
            if (this.tournamentRepository.findById(tournamentId).get().getFens().size() > 0) {
                Set<Fen> fens = this.tournamentRepository.findById(tournamentId).get().getFens();
                Set<FenDto> approvedFens = new HashSet<>();
                for (Fen fen : fens) {
                    if (fen.getStatus().name().equals("APPROVED")) { //todo change to APPROVED
                        FenDto changedFen = new FenDto(fen.getId(), fen.getFen(), fen.getDescription(), fen.getMaxPoints(), fen.getStatus());
                        approvedFens.add(changedFen);

                    }

                }
                return approvedFens;
            }
        }


        return null;
    }

    @Override
    public Set<FenDto> listFensByTournamentName(String name) {
        if (this.tournamentRepository.findByName(name).isPresent()) {
            if (this.tournamentRepository.findByName(name).get().getFens().size() > 0) {
                Set<Fen> fens = this.tournamentRepository.findByName(name).get().getFens();
                Set<FenDto> approvedFens = new HashSet<>();
                for (Fen fen : fens) {
                    //hack (( fen.getSolution() == null || "".equals(fen.getSolution())) &&
                    if (fen.getStatus().name().equals("APPROVED")) {
                        FenDto changedFen = new FenDto(fen.getId(), fen.getFen(), fen.getDescription(), fen.getMaxPoints(), fen.getStatus());
                        approvedFens.add(changedFen);

                    }

                }
                return approvedFens;
            }
        }


        return null;
    }


    @Override
    public Tournament findTournamentByName(String name) throws InvalidTournament {
        return tournamentRepository.findByName(name).orElseThrow(() -> new InvalidTournament(name));
    }


    @Override
    public Optional<Tournament> edit(String id, Tournament tournament) {
        Tournament tournament1 = this.tournamentRepository.findById(id).orElseThrow(TournamentNotFound::new);


        tournament1.setName(tournament.getName());
        tournament1.setDate(tournament.getDate());
        tournament1.setTournamentActive(tournament.isTournamentActive());
        tournament1.setDuration(tournament.getDuration());

        return Optional.of(this.tournamentRepository.save(tournament1));

    }

    @Override
    public Optional<Tournament> save(Tournament tournament) {
        if (this.tournamentRepository.findByName(tournament.getName()).isPresent()) {
            throw new InvalidTournament(tournament.getName());
        }
        Tournament tournament1 = new Tournament(tournament.getName(), tournament.isTournamentActive(), tournament.getDate(), tournament.getDuration());
        List<Fen> fens = this.fenRepository.findAll();
        Set<Fen> fenSet = new HashSet<>(fens);

        tournament1.setFens(fenSet);

        return Optional.of(this.tournamentRepository.save(tournament1));


    }


}