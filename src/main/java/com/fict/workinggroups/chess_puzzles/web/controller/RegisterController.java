package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidArgumentsException;
import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameOrPasswordException;
import com.fict.workinggroups.chess_puzzles.exception.PasswordsDoNotMatchException;
import com.fict.workinggroups.chess_puzzles.exception.UsernameAlreadyExistsException;
import com.fict.workinggroups.chess_puzzles.model.enums.Role;
import com.fict.workinggroups.chess_puzzles.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {


    private final UserService userService;

    public RegisterController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        // model.addAttribute("bodyContent","register");
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam Role role) {
        try {

            this.userService.register(username, password, repeatedPassword, role);
            return "redirect:/login?success=AccountSuccessfullyCreated";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException |
                 InvalidUsernameOrPasswordException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}


