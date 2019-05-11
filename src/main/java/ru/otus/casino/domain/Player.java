package ru.otus.casino.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String bet;
    private boolean win;

    public Player(String name, String bet){
        this.name = name;
        this.bet = bet;
    }
}
