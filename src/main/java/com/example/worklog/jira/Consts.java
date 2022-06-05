package com.example.worklog.jira;

public class Consts {
    private Consts()
    {
    }

    //Jira Api properties
    public static final String JIRA_URL = "";

    //Сообщения
    public static final String START_MSG = "Этот бот предназначен для сервиса Jira " + "\r\n"
            + "Чтобы получить доступ к возможностям бота отправьте свой логин от учетной записи!";
    public static final String REG_OK_MSG = "Успешно! Теперь Вам доступны все возможности бота Jira Bot!";
    public static final String UNKNOWN_MSG = "Что вы от меня хотите" ;
    public static final String ERROR_MSG = "У Вас нет доступа к данному функционалу!" +
            "Чтобы получить доступ к возможностям бота отправьте свой логин от учетной записи!";

}
