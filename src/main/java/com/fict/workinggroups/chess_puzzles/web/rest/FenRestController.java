package com.fict.workinggroups.chess_puzzles.web.rest;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/fens")
@RestController
public class FenRestController {

    @Autowired
    private FenService fenService;

    @GetMapping
    public List<Fen> getAllFens() {


        return fenService.getAllFens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fen> getFenById(@PathVariable String id) {
        {
            try {
                Optional<Fen> fen = fenService.getFenById(id);
                if (!fen.isEmpty()) {
                    return ResponseEntity.ok().body(fen.get());
                } else {
                    return ResponseEntity.status(422).body(null);
                }
            } catch (FenNotFound e) {
                return ResponseEntity.status(422).body(null);
            }
        }
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Fen> saveFen(@RequestBody FenSolutionDto fenSolutionDto) {
        return this.fenService.save(fenSolutionDto)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Fen> editFen(@PathVariable String id, @ModelAttribute FenSolutionDto fenSolutionDto) {
        return this.fenService.edit(id, fenSolutionDto)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/addSolution/{id}")
    public ResponseEntity<FenDto> addFenSolution(@PathVariable String id, @RequestParam String solution) {


        return this.fenService.addFenSolution(id, solution)
                .map(fen -> ResponseEntity.ok().body(new FenDto(fen.getId(), fen.getFen(), fen.getDescription(),
                        fen.getMaxPoints(), fen.getStatus())))
                .orElseGet(() -> ResponseEntity.badRequest().build());


    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFen(@PathVariable String id) {
        try {
            log.debug("deleteFen "+id);
            fenService.deleteFen(id);
            return ResponseEntity.ok().body(id);
        } catch (FenNotFound e) {

            return ResponseEntity.status(422).body(e.getMessage());
        } catch (Exception e){
            log.error("qqq An ERROR Message "+e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    //hacks for import export of fen data
    @GetMapping("/allfens")
    public List<Fen> getAllFen() {
        return this.fenService.getAllFensWithSolution();
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Fen> approve(@PathVariable String id) {
        log.debug("update fen approve "+id);
        return this.fenService.changeStatusToApproved(id)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/decline/{id}")
    public ResponseEntity<Fen> decline(@PathVariable String id) {
        log.debug("update fen decline "+id);
        return this.fenService.changeStatusToDeclined(id)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

