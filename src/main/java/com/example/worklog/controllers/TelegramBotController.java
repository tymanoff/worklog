package com.example.worklog.controllers;

import com.example.worklog.jira.Consts;
import com.example.worklog.jira.UserData;
import com.example.worklog.jira.UserDataCache;
import com.example.worklog.jira.WorkLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
@RequiredArgsConstructor
public class TelegramBotController {
    private final WorkLog workLog;
    private final UserDataCache userDataCache;

    public SendMessage doSomeAction(Message message) {
        String messageText = message.getText();
        long userId = message.getFrom().getId();
        long chatId = message.getChatId();
        UserData userData = userDataCache.getUserData(message.getChatId());

        if (messageText.equals("/start")) {
            return answer(message, Consts.START_MSG);
        }
        if(messageText.equals("/worklog")){
            workLog.asWorkLog(userData);
            return answer(message, Consts.DONE);
        }
        if(messageText.equals("/help")){
            return answer(message, Consts.HELP);
        }
        if(messageText.equals("/issue")){
            return answer(message, Consts.ISSUE);
        }
        if (messageText.matches("[A-Z]{3}-\\d")){
            userData.setIssue(messageText);
            userDataCache.saveUserData(userId,userData);
            return answer(message, Consts.WORKLOG);
        }
        if (messageText.matches("[1-9]h [1-5]\\dm")||messageText.matches("[1-9]h")){
            userData.setWorkLog(messageText);
            userDataCache.saveUserData(userId,userData);
            return answer(message, Consts.TEXT);
        }

        return answer(message, Consts.UNKNOWN_MSG);
    }

    private SendMessage answer(Message message, String text) {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(String.valueOf(message.getChatId()));
        sMessage.setText(text);
        return sMessage;
    }
}
