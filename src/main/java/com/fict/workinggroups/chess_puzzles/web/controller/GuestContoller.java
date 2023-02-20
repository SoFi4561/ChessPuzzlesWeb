package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestContoller {
    @Autowired
    private UserService userService;

    @GetMapping("/guestPage")
    public String viewHomePage(Model model) {
        User user = new User();
        model.addAttribute("Guest", user);

        return "guestpage";
    }

    @GetMapping("/guestGame")
    public String viewGuestGame(Model model, String id) {
        model.addAttribute("Guest", userService.getGuest(id));


        return "tournament_list";
    }

    @PostMapping("/homepage")
    public String saveGuest(@ModelAttribute("Guest") User guest, Model model) {

        userService.saveGuest(guest);
        return "home";

    }
}
