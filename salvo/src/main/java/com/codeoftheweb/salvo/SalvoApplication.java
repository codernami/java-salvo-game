package com.codeoftheweb.salvo;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class SalvoApplication {


    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {

			// Players
            Player player1 = playerRepository.save(new Player("Jack", "Bauer", "j.bauer@ctu.gov",passwordEncoder.encode("24"), true));
			Player player2 = playerRepository.save(new Player("Chloe", "O'Brian", "c.obrian@ctu.gov",passwordEncoder.encode("42")));
			Player player3 = playerRepository.save(new Player("Kim", "Bauer", "kim_bauer@gmail.com",passwordEncoder.encode("kb")));
			Player player4 = playerRepository.save(new Player("Tony", "Almeida", "t.almeida@ctu.gov",passwordEncoder.encode("mole")));

			//Games
			Game game1 = gameRepository.save(new Game(LocalDateTime.now()));
            Game game2 = gameRepository.save(new Game(LocalDateTime.now().plusHours(1)));
            Game game3 = gameRepository.save(new Game(LocalDateTime.now().plusHours(2)));
            Game game4 = gameRepository.save(new Game(LocalDateTime.now().plusHours(3)));
            Game game5 = gameRepository.save(new Game(LocalDateTime.now().plusHours(4)));
            Game game6 = gameRepository.save(new Game(LocalDateTime.now().plusHours(5)));
            Game game7 = gameRepository.save(new Game(LocalDateTime.now().plusHours(6)));
            Game game8 = gameRepository.save(new Game(LocalDateTime.now().plusHours(7)));

            //Gameplayers
            GamePlayer gp1=gamePlayerRepository.save(new GamePlayer(game1,player1,LocalDateTime.now()));
            GamePlayer gp2=gamePlayerRepository.save(new GamePlayer(game1,player2,LocalDateTime.now()));

            GamePlayer gp3=gamePlayerRepository.save(new GamePlayer(game2,player1,LocalDateTime.now()));
            GamePlayer gp4=gamePlayerRepository.save(new GamePlayer(game2,player2,LocalDateTime.now()));

            GamePlayer gp5=gamePlayerRepository.save(new GamePlayer(game3,player2,LocalDateTime.now()));
            GamePlayer gp6=gamePlayerRepository.save(new GamePlayer(game3,player4,LocalDateTime.now()));

            GamePlayer gp7=gamePlayerRepository.save(new GamePlayer(game4,player2,LocalDateTime.now()));
            GamePlayer gp8=gamePlayerRepository.save(new GamePlayer(game4,player1,LocalDateTime.now()));

            GamePlayer gp9=gamePlayerRepository.save(new GamePlayer(game5,player4,LocalDateTime.now()));
            GamePlayer gp10=gamePlayerRepository.save(new GamePlayer(game5,player1,LocalDateTime.now()));

            GamePlayer gp11=gamePlayerRepository.save(new GamePlayer(game6,player2,LocalDateTime.now()));

            GamePlayer gp12=gamePlayerRepository.save(new GamePlayer(game7,player4,LocalDateTime.now()));

            GamePlayer gp13=gamePlayerRepository.save(new GamePlayer(game8,player2,LocalDateTime.now()));
            GamePlayer gp14=gamePlayerRepository.save(new GamePlayer(game8,player4,LocalDateTime.now()));


            //Game1
            //Ships, ShipLocation, type
            gp1.addShip(new Ship(Arrays.asList("H2","H3","H4"),"destroyer"));
            gp1.addShip(new Ship(Arrays.asList("E1","F1","G1"),"submarine"));
            gp1.addShip(new Ship(Arrays.asList("B4","B5"),"patrol_boat"));

            gp2.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp2.addShip(new Ship(Arrays.asList("F1","F2"),"patrol_boat"));

            //Salvoes, turn, SalvoLocation
            gp1.addSalvo(new Salvo(1,Arrays.asList("B5","C5","F1")));
            gp1.addSalvo(new Salvo(2,Arrays.asList("F2","D5")));

            gp2.addSalvo(new Salvo(1,Arrays.asList("B4","B5","B6")));
            gp2.addSalvo(new Salvo(2,Arrays.asList("E1","H3","A2")));

            gamePlayerRepository.save(gp1);
            gamePlayerRepository.save(gp2);

            //Scores
            scoreRepository.save(new Score( 1.0,game1, player1, LocalDateTime.now()));
            scoreRepository.save(new Score( 0.0,game1, player2, LocalDateTime.now()));

            scoreRepository.save(new Score( 0.5,game2, player1, LocalDateTime.now()));
            scoreRepository.save(new Score( 0.5,game2, player2, LocalDateTime.now()));

            scoreRepository.save(new Score( 1.0,game3, player2, LocalDateTime.now()));
            scoreRepository.save(new Score( 0.0,game3, player4, LocalDateTime.now()));

            scoreRepository.save(new Score( 0.5,game4, player2, LocalDateTime.now()));
            scoreRepository.save(new Score( 0.5,game4, player1, LocalDateTime.now()));


            //Game2
            //Ships
            gp3.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp3.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gp4.addShip(new Ship(Arrays.asList("A2","A3","A4"),"submarine"));
            gp4.addShip(new Ship(Arrays.asList("G6","H6"),"patrol_boat"));


            //Salvoes
            gp3.addSalvo(new Salvo(1,Arrays.asList("A2","A4","G6")));
            gp3.addSalvo(new Salvo(2,Arrays.asList("A3","H6")));

            gp4.addSalvo(new Salvo(1,Arrays.asList("B5","D5","C7")));
            gp4.addSalvo(new Salvo(2,Arrays.asList("C5","C6")));

            gamePlayerRepository.save(gp3);
            gamePlayerRepository.save(gp4);

            //Game3
            //Ships
            gp5.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp5.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gp6.addShip(new Ship(Arrays.asList("A2","A3","A4"),"submarine"));
            gp6.addShip(new Ship(Arrays.asList("G6","H6"),"patrol_boat"));

            //Salvoes
            gp5.addSalvo(new Salvo(1,Arrays.asList("G6","H6","A4")));
            gp5.addSalvo(new Salvo(2,Arrays.asList("A2","A3","D8")));

            gp6.addSalvo(new Salvo(1,Arrays.asList("H1","H2","H3")));
            gp6.addSalvo(new Salvo(2,Arrays.asList("E1","F2","G3")));

            gamePlayerRepository.save(gp5);
            gamePlayerRepository.save(gp6);

            //Game4

            //Ships
            gp7.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp7.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gp8.addShip(new Ship(Arrays.asList("A2","A3","A4"),"submarine"));
            gp8.addShip(new Ship(Arrays.asList("G6","H6"),"patrol_boat"));

            //Salvoes
            gp7.addSalvo(new Salvo(1,Arrays.asList("A3","A4","F7")));
            gp7.addSalvo(new Salvo(2,Arrays.asList("A2","G6","H6")));

            gp8.addSalvo(new Salvo(1,Arrays.asList("B5","C6","H1")));
            gp8.addSalvo(new Salvo(2,Arrays.asList("C5","C7","D5")));

            gamePlayerRepository.save(gp7);
            gamePlayerRepository.save(gp8);

            //Game5

            //Ships
            gp9.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp9.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gp10.addShip(new Ship(Arrays.asList("A2","A3","A4"),"submarine"));
            gp10.addShip(new Ship(Arrays.asList("G6","H6"),"patrol_boat"));

            //Salvoes
            gp9.addSalvo(new Salvo(1,Arrays.asList("A1","A2","A3")));
            gp9.addSalvo(new Salvo(2,Arrays.asList("G6","G7","G8")));

            gp10.addSalvo(new Salvo(1,Arrays.asList("B5","B6","C7")));
            gp10.addSalvo(new Salvo(2,Arrays.asList("C6","D6","E6")));

            gp10.addSalvo(new Salvo(3,Arrays.asList("H1","H8")));

            gamePlayerRepository.save(gp9);
            gamePlayerRepository.save(gp10);

            //Game6
            gp11.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp11.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gamePlayerRepository.save(gp11);

            //Game8
            gp13.addShip(new Ship(Arrays.asList("B5","C5","D5"),"destroyer"));
            gp13.addShip(new Ship(Arrays.asList("C6","C7"),"patrol_boat"));

            gp14.addShip(new Ship(Arrays.asList("A2","A3","A4"),"submarine"));
            gp14.addShip(new Ship(Arrays.asList("G6","H6"),"patrol_boat"));

            gamePlayerRepository.save(gp13);
            gamePlayerRepository.save(gp14);

        };
	}
}
