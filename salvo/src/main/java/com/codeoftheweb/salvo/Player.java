package com.codeoftheweb.salvo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean admin;

    //Relación con GamePlayer mapped by player
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    //Relación con Score mapped by player
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Score> scores = new HashSet<>();

    //Constructores, por defecto y el segundo: firstName, lastName, userName, password
    public Player() {
    }

    public Player(String firstName, String lastName, String userName, String password, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.admin = isAdmin;
    }

    //constructor que por defecto User por eso false
    public Player(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.admin = false;
    }


    //getters & setters
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }


    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }

    //método para establecer la relación entre un objeto Player y un objeto GamePlayer
    public void addGamePlayer(GamePlayer gamePlayer) {
        //se agrega el gamePlayer que ingresa como parámetro al set declarado en los atributos
        this.gamePlayers.add(gamePlayer);
        //al gamePlayer ingresado se le agrega este player mediante su setter en la clase GamePlayer
        gamePlayer.setPlayer(this);
    }

    public Set<Score> getScores(){
        return this.scores;
    }

    public void addScore(Score score){
        this.scores.add(score);
        score.setPlayer(this);
    }

    public Score getScoreByGame(Game game) {
        return this.scores.stream().filter(score -> score.getGame().getId() == game.getId() ).findFirst().orElse(null);

    }


    //método que retorna todos los games relacionados con el player a partir de los gamePlayers
    @JsonIgnore
    public List<Game> getGames() {
        return this.gamePlayers.stream().map(x -> x.getGame()).collect(Collectors.toList());
    }


    //Task2 5- DTO para players, solo paso id de Player y userName
    public Map<String, Object> playerDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id_player", this.id);
        dto.put("username", this.userName);
        dto.put("firstname", this.firstName);
        return dto;
    }

}