package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class PlayedFenController {


    @Autowired
    private PlayedFensService playedFensService;


    @GetMapping("/viewPlayedFen")
    public String viewPlayedFens(Model model) {

        model.addAttribute("playedFen", playedFensService.getAllPlayedFens());
        return "playedFen";
    }

    @GetMapping("/addPlayedFen")
    public String addPlayedFen(Model model) {
        PlayedFen playedFen = new PlayedFen();
        model.addAttribute("playedFen", playedFen);
        return "add_playedFen";
    }

    @PostMapping("/savePlayedFen")
    public String savePlayedFen(@ModelAttribute("playedFen") PlayedFen playedFen, Model model) {
        try {
            playedFensService.savePlayedFen(playedFen);
            playedFensService.updateLeaderboard(playedFen);

            return "redirect:viewPlayedFen";
        } catch (PlayerNotFound e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "add_playedFen";
        }

    }


}



