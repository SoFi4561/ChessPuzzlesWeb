package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.WrongFenSolutionException;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/playedFen")
public class PlayedFenRestController {

    @Autowired
    private PlayedFensService playedFensService;

    @PostMapping("/makeAMove")
    public ResponseEntity makeAMove(@RequestBody PlayedFenDto playedFenDto) {
        try {
            log.debug("qqq PlayedFenDto " + playedFenDto.toString());
            playedFensService.updateLeaderboard(playedFenDto);
            return ResponseEntity.ok().body(playedFenDto);
        } catch (WrongFenSolutionException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        } catch (Exception e) {
            log.error("qqq An ERROR Message "+e.getMessage());
            log.error("qqq An ERROR Message "+e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}