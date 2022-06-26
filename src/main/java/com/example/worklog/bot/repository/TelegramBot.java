package com.example.worklog.bot.repository;

import com.example.worklog.controllers.TelegramBotController;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotController telegramBotController;
    private String botName;
    private String token;

    public TelegramBot(TelegramBotController telegramBotController, String botName, String token) {
        this.telegramBotController = telegramBotController;
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
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            SendMessage answer = telegramBotController.doSomeAction(message);
            if (answer != null) {
                try {
                    execute(answer);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
