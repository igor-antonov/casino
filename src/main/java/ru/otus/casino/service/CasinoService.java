package ru.otus.casino.service;

import org.springframework.stereotype.Service;
import ru.otus.casino.domain.Player;

@Service
public interface CasinoService {
    void spinRoulette(Player player);
}
