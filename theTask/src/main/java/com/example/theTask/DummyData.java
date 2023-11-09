package com.example.theTask;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static List<ChatLog> chatLogs = new ArrayList<>();

    static {
        ChatLog log1 = new ChatLog("Hello!", System.currentTimeMillis(), true, "1", "user1");
        ChatLog log2 = new ChatLog("Hi there!", System.currentTimeMillis(), false, "2", "user2");
        ChatLog log3 = new ChatLog("How are you?", System.currentTimeMillis(), true, "3", "user1");
        ChatLog log4 = new ChatLog("I'm good, thanks!", System.currentTimeMillis(), false, "4", "user2");
        ChatLog log5 = new ChatLog("Nice weather today.", System.currentTimeMillis(), true, "5", "user1");
        ChatLog log6 = new ChatLog("Hey!", System.currentTimeMillis(), true, "6", "user3");
        ChatLog log7 = new ChatLog("What's up?", System.currentTimeMillis(), false, "7", "user4");
        ChatLog log8 = new ChatLog("Good morning!", System.currentTimeMillis(), true, "8", "user3");
        ChatLog log9 = new ChatLog("Good evening!", System.currentTimeMillis(), false, "9", "user4");
        ChatLog log10 = new ChatLog("How's it going?", System.currentTimeMillis(), true, "10", "user3");

        chatLogs.add(log1);
        chatLogs.add(log2);
        chatLogs.add(log3);
        chatLogs.add(log4);
        chatLogs.add(log5);
        chatLogs.add(log6);
        chatLogs.add(log7);
        chatLogs.add(log8);
        chatLogs.add(log9);
        chatLogs.add(log10);
    }

    public static List<ChatLog> getChatLogs() {
        return chatLogs;
    }
}
