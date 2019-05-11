package ru.otus.casino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.casino.domain.Player;
import ru.otus.casino.repository.PlayerRepository;

@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class CasinoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(CasinoApplication.class, args);
        Casino casino = ctx.getBean(Casino.class);
        PlayerRepository playerRepository = ctx.getBean(PlayerRepository.class);
        playerRepository.save(new Player("Первый игрок", "red"));

        while (true) {
            Thread.sleep(5000);
            playerRepository.findAll().forEach(player -> {
                player.setWin(casino.process(player));
                System.out.println("player: " + player.getName() + " result: " + player.isWin());
                playerRepository.save(player);
            });
        }
    }
}
