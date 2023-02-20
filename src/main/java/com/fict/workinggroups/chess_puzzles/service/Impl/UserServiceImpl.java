package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameException;
import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameOrPasswordException;
import com.fict.workinggroups.chess_puzzles.exception.PasswordsDoNotMatchException;
import com.fict.workinggroups.chess_puzzles.exception.UsernameAlreadyExistsException;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.model.enums.Role;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.repository.UserRepository;
import com.fict.workinggroups.chess_puzzles.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveGuest(User guest) {
        Pattern pattern = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher matcher = pattern.matcher(guest.getUsername());
        if (!matcher.matches()) {
            throw new InvalidUsernameException();
        }
        if (this.userRepository.findByUsername(guest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(guest.getUsername());
        }
        Player player = new Player(guest.getUsername());
        //player.setUserId(guest);
        this.playerRepository.save(player);

        return this.userRepository.save(guest);


    }


    @Override
    public User register(String username, String password, String repeatPassword, Role role) {
        Pattern pattern = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), role);

        Player player = new Player(username);
        //player.setUserId(user);
        this.playerRepository.save(player);

        return userRepository.save(user);
    }


    public Optional<User> getGuest(String id) {
        return userRepository.findById(id);
    }
}

