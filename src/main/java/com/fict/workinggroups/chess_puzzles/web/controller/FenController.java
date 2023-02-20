package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FenController {
    @Autowired
    private FenService fenService;

    @GetMapping("/viewFens")
    public String viewHomePage(Model model) {
        model.addAttribute("fenList", fenService.getAllFensWithSolution());
        return "fen_list";
    }

    @GetMapping("/newFenForm")
    public String newFenForm(Model model) {
        Fen fen = new Fen();
        model.addAttribute("fen", fen);
        return "add_fen";
    }

    @PostMapping("/saveFen")
    public String saveFen(@ModelAttribute("fen") Fen fen, Model model, @RequestParam(required = false) String id) {

        try {
            if (id != null) {
                fenService.edit(id, fen.getFen(), fen.getDescription(), fen.getMaxPoints(), fen.getSolution());
            } else {
                fenService.saveFen(fen.getFen(), fen.getDescription(), fen.getMaxPoints(), fen.getSolution());

            }
            return "redirect:/viewFens";

        } catch (InvalidFenException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "redirect:/add_fen?error=" + e.getMessage();
        }


    }


    @PutMapping("/updateForm/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String UpdateForm(@PathVariable(value = "id") String id, Model model) {
        Fen fen = fenService.findById(id).get();
        model.addAttribute("fen", fen);
        return "edit_fen";
    }


    @DeleteMapping("/deleteFenID/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFenId(@PathVariable(value = "id") String id) {
        this.fenService.deleteFen(id);
        return "redirect:/viewFens";
    }
}
