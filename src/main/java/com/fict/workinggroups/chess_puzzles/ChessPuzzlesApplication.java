package com.fict.workinggroups.chess_puzzles;


import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ChessPuzzlesApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChessPuzzlesApplication.class, args);

//        TestMoves testMoves = new TestMoves();
//
//        System.out.println(testMoves.getBoardModel());
    }

    @Profile("!prod")
    @Bean
    public CommandLineRunner dataLoader(FenRepository fenRepository) {
        return args -> {
//            fenRepository.save(new Fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "The Starting Position", 500, "A1-B2"));
//            fenRepository.save(new Fen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "This is after the move 1.e4", 500, "A1-B2"));
//            fenRepository.save(new Fen("3RQ3/pp3pk1/6p1/2q4p/5PbK/P1r5/2n3PP/7R b – – 0 1", "Black to play and mate white", 500, "A1-B2"));
//            fenRepository.save(new Fen("5k2/ppp5/4P3/3R3p/6P1/1K2Nr2/PP3P2/8 b - - 1 32", "The final position", 500, "A1-B2"));
//            fenRepository.save(new Fen("r3r3/3nkp2/q1p1p1p1/p3P1P1/PbpB4/1P5R/2P2Q1P/3RN2K w – – 0 1", "The checkmate, White to play and mate Black.", 500, "A1-B2"));
//            fenRepository.save(new Fen("3rr1nk/1R2q3/3b3p/4np2/2Pp2pP/P2P2P1/5PBK/1RBQ2N1 b – – 0 1", "Black to play and win", 500, "A1-B2"));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}