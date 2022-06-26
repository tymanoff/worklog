package com.example.worklog.configuration;

import com.example.worklog.bot.repository.TelegramBot;
import com.example.worklog.controllers.TelegramBotController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class TelegramBotConfig {

    private final TelegramBotController telegramBotController;

    @Value("${bot.name}") // hide user
    private String botName;

    @Value("${bot.token}") // hide token
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(telegramBotController, botName, token);
    }
}
