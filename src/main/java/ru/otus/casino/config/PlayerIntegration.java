package ru.otus.casino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.stereotype.Component;
import ru.otus.casino.domain.Player;

@Component
public class PlayerIntegration {

    @Bean
    public QueueChannel playersChannel() {
        return MessageChannels.queue(10).datatype(Player.class).get();
    }

    @Bean
    public PublishSubscribeChannel resultChannel() {
        return MessageChannels.publishSubscribe().datatype(Boolean.class).get();
    }

    @Bean (name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(1000).get() ;
    }

    @Bean
    public IntegrationFlow casinoFlow() {
        return IntegrationFlows.from("playersChannel")
                .handle("casinoService", "spinRoulette")
                .channel("resultChannel")
                .get();
    }
}
