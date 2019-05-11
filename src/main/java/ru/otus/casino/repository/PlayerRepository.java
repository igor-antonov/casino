package ru.otus.casino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.casino.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
