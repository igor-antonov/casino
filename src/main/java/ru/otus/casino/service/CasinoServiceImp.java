package ru.otus.casino.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import ru.otus.casino.domain.Player;
import ru.otus.casino.repository.PlayerRepository;

@Service
@EnableScheduling
public class CasinoServiceImp implements CasinoService {

    @Autowired
    private PlayerRepository playerRepository;

    private final Logger log = LoggerFactory.getLogger(CasinoServiceImp.class);

    public void spinRoulette(Player player){
        String[] result = new String[]{"red", "black"};
        int n = (int)Math.floor(Math.random() * result.length);
        player.setWin(player.getBet().equals(result[n]));

        playerRepository.save(player);
        log.info("player: " + player.getName() + "; result: " + player.isWin());
    }
}
