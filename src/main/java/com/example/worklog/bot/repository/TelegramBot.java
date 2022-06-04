package com.example.worklog.bot.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}") // hide user
    private String botName;

    @Value("${bot.token}") // hide token
    private String token;

    public TelegramBot(@Value("${bot.name}") String botName, @Value("${bot.token}") String token) {
        super();
        this.botName = botName;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            try {
                execute(new SendMessage(String.valueOf(chat_id), "Hi you send: " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
