package ru.otus.casino.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.otus.casino.Casino;
import ru.otus.casino.repository.PlayerRepository;

@IntegrationComponentScan
@EnableIntegration
@EnableScheduling
public class RollServiceImp implements RollService {

    @Autowired
    private Casino casino;
    @Autowired
    private PlayerRepository playerRepository;

    private final Logger log = LoggerFactory.getLogger(RollServiceImp.class);

    @Scheduled(fixedRate = 5000)
    public void roll(){
        log.info("start");
        playerRepository.findAll().forEach(player -> {
            casino.process(player);
        });
    }
}
