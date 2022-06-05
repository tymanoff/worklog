package com.example.worklog.bot.repository;

import com.example.worklog.jira.Consts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramBotController {
    public static SendMessage doSomeAction(Message message)
    {
        String messageText = message.getText();
        if (messageText.equals("/start"))
        {
            return startAnswer(message, Consts.START_MSG);
        }

        return answer(message, Consts.UNKNOWN_MSG);
    }

    private static SendMessage startAnswer(Message message, String text)
    {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(String.valueOf(message.getChatId()));
        sMessage.setText(text);
        return sMessage;
    }
    private static SendMessage answer(Message message, String text)
    {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(String.valueOf(message.getChatId()));
        sMessage.setText(text);
        return sMessage;
    }
}
