package com.example.theTask;

import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ChatLogController {

    private List<ChatLog> chatLogs = DummyData.getChatLogs();

    @GetMapping("/chatlogs/{user}")
    public List<ChatLog> getChatLogs(
            @PathVariable String user,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(required = false) String start
    ) {
        List<ChatLog> userChatLogs = chatLogs.stream()
                .filter(chatLog -> chatLog.getUser().equals(user))
                .sorted((cl1, cl2) -> cl2.getTimestamp().compareTo(cl1.getTimestamp()))
                .collect(Collectors.toList());

        if (limit < userChatLogs.size()) {
            userChatLogs = userChatLogs.subList(0, limit);
        }

        if (start != null) {
            userChatLogs = userChatLogs.stream()
                    .filter(chatLog -> chatLog.getMessageID().compareTo(start) < 0)
                    .collect(Collectors.toList());
        }

        return userChatLogs;
    }

    @PostMapping("/chatlogs/{user}")
    public String createChatLog(@PathVariable String user, @RequestBody ChatLogRequest request) {
        String message = request.getMessage();
        Long timestamp = request.getTimestamp();
        Boolean isSent = request.getIsSent();
        String messageID = generateUniqueMessageID();
        ChatLog chatLog = new ChatLog(message, timestamp, isSent, messageID, user);

        chatLogs.add(chatLog);

        return messageID;
    }


    @DeleteMapping("/chatlogs/{user}")
    public String deleteChatLogs(@PathVariable String user) {
        chatLogs.removeIf(chatLog -> chatLog.getUser().equals(user));
        return "Deleted all chat logs for user " + user;
    }

    @DeleteMapping("/chatlogs/{user}/{messageID}")
    public String deleteChatLog(@PathVariable String user, @PathVariable String messageID) {
        chatLogs.removeIf(chatLog -> chatLog.getMessageID().equals(messageID) && chatLog.getUser().equals(user));
        return "Deleted chat log with message ID " + messageID + " for user " + user;
    }

    private String generateUniqueMessageID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
