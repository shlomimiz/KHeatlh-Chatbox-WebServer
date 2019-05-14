package Entities;

import java.util.HashMap;
import java.util.Map;

public class UsersSessions {

    private final static Map<String, Integer> activeSessions = new HashMap<>();

    private static UsersSessions instance = null;

    private UsersSessions() {}

    public static UsersSessions getInstance() {
        if (instance == null) {
            synchronized(UsersSessions.class) {
                if (instance == null) {
                    instance = new UsersSessions();
                }
            }
        }
        return instance;
    }

    public Map<String, Integer> getActiveSessions(){
        return activeSessions;
    }


}
