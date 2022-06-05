package com.example.worklog.bot.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}") // hide user
    private final String botName;

    @Value("${bot.token}") // hide token
    private final String token;

    public TelegramBot(@Value("${bot.name}") String botName, @Value("${bot.token}") String token)
    {
        super();
        this.botName = botName;
        this.token = token;
    }

    @Override
    public String getBotUsername()
    {
        return botName;
    }

    @Override
    public String getBotToken()
    {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        Message message = update.getMessage();
        if (message != null && message.hasText())
        {
            SendMessage answer = TelegramBotController.doSomeAction(message);
            if (answer != null)
            {
                try
                {
                    execute(answer);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
