package com.example.worklog.jira;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WorkLog {

    @Value("${jira.user}") // hide user
    private String user;

    @Value("${jira.tok}") // hide token
    private String tok;

    @Value("${jira.urljira}") // hide url
    private String url;

    @Value("${jira.urlissue}") // hide issue
    private String urlIssue;

    public void asWorkLog(UserData userData) {
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            payload.put("timeSpent", userData.getWorkLog());
            payload.put("started", "2022-01-13T13:41:00.000+0000");

            ObjectNode comment = payload.putObject("comment");
            {
                comment.put("version", 1);
                comment.put("type", "doc");

                ArrayNode contentArr = comment.putArray("content");
                ObjectNode contentOb1 = contentArr.addObject();
                {
                    contentOb1.put("type", "paragraph");

                    ArrayNode contentArr2 = contentOb1.putArray("content");
                    ObjectNode contentOb2 = contentArr2.addObject();
                    {
                        contentOb2.put("type", "text");
                        contentOb2.put("text", "TestTelegram");
                    }
                }
            }
        }

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            HttpResponse<JsonNode> response = Unirest.post(url + urlIssue + userData.getIssue() + "/worklog")
                    .basicAuth(user, tok)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
