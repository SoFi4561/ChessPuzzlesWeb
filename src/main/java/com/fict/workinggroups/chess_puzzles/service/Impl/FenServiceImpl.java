package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class FenServiceImpl implements FenService {

    @Autowired
    private FenRepository fenRepo;

    @Autowired
    private TournamentRepository tournamentRepository;


    @Override
    public List<Fen> getAllFens() {
        List<Fen> fens = this.fenRepo.findAll();
        //hacks List<Fen> fens = this.fenRepo.findBySolutionIsNull();
        List<Fen> Fens = new ArrayList<>();
        for (Fen fen : fens) {
            Fen fen1 = new Fen(fen.getId(), fen.getFen(), fen.getDescription(), fen.getMaxPoints());
            Fens.add(fen1);
        }


        return Fens;
    }

    @Override
    public List<Fen> getAllFensWithSolution() {
        return fenRepo.findAll();
    }

    @Override
    public Optional<Fen> saveFen(String fen, String description, Integer maxPoints, String solution) {
        if (isValidFen(fen)) {
            Fen newFen = new Fen(fen, description, maxPoints, solution);
            this.fenRepo.save(newFen);
            return Optional.of(newFen);
        } else {
            throw new InvalidFenException();
        }
    }

    @Override
    public Optional<Fen> getFenById(String id) {
        if (fenRepo.findById(id).isPresent()) {
            return this.fenRepo.findById(id);

        } else {
            throw new FenNotFound();
        }
    }

    @Override
    public Optional<Fen> findById(String id) {
        return this.fenRepo.findById(id);
    }

    @Override
    public String deleteFen(String id) {
        //todo
        List<Tournament> tournaments = tournamentRepository.findAll();
        for (Tournament t : tournaments) {
            log.debug("deleteFen fen id "+id);
            log.debug("deleteFen tournamentID "+t.getId());
            List<Fen> forRemove = t.getFens().stream().filter(it -> it.getId() == id).collect(Collectors.toList());
            log.debug("deleteFen forRemove "+forRemove);
            if (forRemove != null && forRemove.size() > 0) {
                Set<Fen> allFensPerTournament = t.getFens();
                log.debug("deleteFen forRemove.get(0)"+forRemove.get(0));
                allFensPerTournament.remove(forRemove.get(0));
                t.setFens(allFensPerTournament);
                tournamentRepository.save(t);
                log.debug("deleteFen tournamentRepository.save(t);");
            }
        }


        if (!fenRepo.findById(id).isEmpty()) {
            this.fenRepo.deleteById(id);
            log.debug("deleteFen deleted");
            return "deleted";//fenRepo.findById(id);
        } else {
            log.debug("deleteFen notDeleted");
            return "not deleted";
        }

    }

    @Override
    public Optional<Fen> edit(String id, String fen, String description, Integer maxPoints, String solution) {
        Fen fenModel = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fenModel.setFen(fen);
        fenModel.setDescription(description);
        fenModel.setMaxPoints(maxPoints);

        fenModel.setSolution(solution);
        return Optional.of(this.fenRepo.save(fenModel));
    }


    public boolean isValidFen(String fen) {
        Pattern pattern = Pattern.compile("((([prnbqkPRNBQK12345678]*/){7})([prnbqkPRNBQK12345678]*)) (w|b) ((K?Q?k?q?)|\\-) (([abcdefgh][36])|\\-) (\\d*) (\\d*)");
        Matcher matcher = pattern.matcher(fen);
        if (!matcher.matches()) {
            System.out.println("Invalid FEN, not following specified format.");
            return false;
        }
        return true;
    }


    @Override
    public Optional<Fen> save(FenSolutionDto fenSolutionDto) {
        if (isValidFen(fenSolutionDto.getFen())) {
            Fen fen = new Fen(fenSolutionDto.getFen(), fenSolutionDto.getDescription(), fenSolutionDto.getMaxPoints(), fenSolutionDto.getSolution());
            fen.setStatus(Status.PENDING);
            this.fenRepo.save(fen);
            return Optional.of(fen);
        } else {
            throw new InvalidFenException();
        }
    }

    @Override
    public Optional<Fen> edit(String id, FenSolutionDto fenSolutionDto) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setFen(fenSolutionDto.getFen());
        fen.setDescription(fenSolutionDto.getDescription());
        fen.setMaxPoints(fenSolutionDto.getMaxPoints());
        fen.setStatus(fenSolutionDto.getStatus());

        return Optional.of(this.fenRepo.save(fen));
    }

    @Override
    public Optional<Fen> addFenSolution(String id, String solution) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setSolution(solution);

        return Optional.of(this.fenRepo.save(fen));
    }

    @Override
    public Optional<Fen> changeStatusToApproved(String id) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setStatus(Status.APPROVED);

        return Optional.of(this.fenRepo.save(fen));
    }

    @Override
    public Optional<Fen> changeStatusToDeclined(String id) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setStatus(Status.DECLINED);

        return Optional.of(this.fenRepo.save(fen));
    }
}
