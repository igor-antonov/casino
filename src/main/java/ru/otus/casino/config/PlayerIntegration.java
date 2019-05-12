package ru.otus.casino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.casino.domain.Player;
import ru.otus.casino.service.RollService;
import ru.otus.casino.service.RollServiceImp;

@Configuration
public class PlayerIntegration {

    @Bean
    public RollService rollService(){
        return new RollServiceImp();
    }

    @Bean
    public QueueChannel playersChannel() {
        return MessageChannels.queue(10).datatype(Player.class).get();
    }

    @Bean
    public PublishSubscribeChannel resultChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean (name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(1000).get() ;
    }

    @Bean
    public IntegrationFlow casinoFlow() {
        return IntegrationFlows.from("playersChannel")
                .handle("casinoServiceImp", "spinRoulette")
                .channel("resultChannel")
                .get();
    }
}
