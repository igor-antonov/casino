package ru.otus.casino.service;

import org.springframework.stereotype.Service;
import ru.otus.casino.domain.Player;

@Service
public class CasinoService {
    public boolean spinRoulette(Player player){
        String[] result = new String[]{"red", "black"};
        int n = (int)Math.floor(Math.random() * result.length);
        return player.getBet().equals(result[n]);
    }
}
