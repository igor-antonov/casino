package ru.otus.casino;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.casino.domain.Player;

@MessagingGateway
public interface Casino {
    @Gateway(requestChannel = "playersChannel", replyChannel = "resultChannel")
    void process(Player player);
}
