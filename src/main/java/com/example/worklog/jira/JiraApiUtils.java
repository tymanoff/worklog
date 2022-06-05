package com.example.worklog.jira;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import org.springframework.beans.factory.annotation.Value;


public class JiraApiUtils
{
    @Value("${jira.user}") // hide user
    private String user;

    private JiraApiUtils()
    {
    }

    private static JiraClient connect;


    //Connect to jira rest api
//    public static void jiraConnect()
//    {
//        BasicCredentials creds = new BasicCredentials( @Value ("${jira.user}") , @Value("${jira.password}"));
//        connect = new JiraClient( Consts.JIRA_URL , creds);
//    }
}
