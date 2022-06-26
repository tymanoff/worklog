package com.example.worklog.jira;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache {
    private Map<Long, UserData> userDataMap = new HashMap<>();

    public void saveUserData(long userId, UserData userData) {
        userDataMap.put(userId, userData);
    }

    public UserData getUserData(long userId) {
        UserData userData = userDataMap.get(userId);
        if (userData == null) {
            userData = new UserData();
        }
        return userData;
    }
}
