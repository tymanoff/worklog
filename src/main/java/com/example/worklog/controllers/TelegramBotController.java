package com.example.worklog.controllers;

import com.example.worklog.jira.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
@RequiredArgsConstructor
public class TelegramBotController {

    public SendMessage doSomeAction(Message message) {
        String messageText = message.getText();
        if (messageText.equals("/start")) {
            return startAnswer(message, Consts.START_MSG);
        }
        return answer(message, Consts.UNKNOWN_MSG);
    }

    private SendMessage startAnswer(Message message, String text) {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(String.valueOf(message.getChatId()));
        sMessage.setText(text);
        return sMessage;
    }

    private SendMessage answer(Message message, String text) {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(String.valueOf(message.getChatId()));
        sMessage.setText(text);
        return sMessage;
    }
}
