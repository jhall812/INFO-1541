package com.example.joshuahallassignment4;

import jakarta.servlet.http.HttpSession;

import java.util.*;

public class SessionListUtil {
    private static final Map<String, HttpSession> SESSIONS = new Hashtable<>();

    public static void addSession(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }

    public static void updateSessionId(HttpSession session, String oldSessionId) {
        synchronized (SESSIONS) {
            SESSIONS.remove(oldSessionId);
            addSession(session);
        }
    }

    public static void removeSession(HttpSession session) {
        SESSIONS.remove(session.getId());
    }

    // loop through all sessions in jsp later
    public static List<HttpSession> getAllSessions() {
        return new ArrayList<>(SESSIONS.values());
    }

    public static int getNumberOfSessions() {
        return SESSIONS.size();
    }
}
