package com.example.theTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatLogController.class)
public class ChatLogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DummyData dummyData;

    @Test
    public void testGetChatLogs() throws Exception {
        // Mocking the DummyData
        List<ChatLog> chatLogs = Arrays.asList(
                new ChatLog("Message 1", 1636454400000L, true, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 2", 1636454500000L, false, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 3", 1636454600000L, true, UUID.randomUUID().toString(), "user2")
        );

        when(dummyData.getChatLogs()).thenReturn(chatLogs);

        mockMvc.perform(get("/chatlogs/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // Assuming user1 has 2 chat logs
    }

    @Test
    public void testCreateChatLog() throws Exception {

        List<ChatLog> chatLogs = Arrays.asList(
                new ChatLog("Message 1", 73547245L, true, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 2", 223546547L, false, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 3", 3463L, true, UUID.randomUUID().toString(), "user2")
        );

        when(dummyData.getChatLogs()).thenReturn(chatLogs);


        ChatLogRequest request = new ChatLogRequest("New Message", 1636454700000L, true, "user1");


        mockMvc.perform(post("/chatlogs/user1")
                        .contentType("application/json")
                        .content("{\"message\":\"New Message\",\"timestamp\":1636454700000,\"isSent\":true,\"user\":\"user1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(request.getMessage()));
    }

    @Test
    public void testDeleteChatLogs() throws Exception {

        List<ChatLog> chatLogs = Arrays.asList(
                new ChatLog("Message 1", 364646300L, true, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 2", 436346034600L, false, UUID.randomUUID().toString(), "user1"),
                new ChatLog("Message 3", 241624124124000L, true, UUID.randomUUID().toString(), "user2")
        );

        when(dummyData.getChatLogs()).thenReturn(chatLogs);


        mockMvc.perform(delete("/chatlogs/user1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted all chat logs for user user1"));
    }

    @Test
    public void testDeleteChatLog() throws Exception {

        List<ChatLog> chatLogs = Arrays.asList(
                new ChatLog("Message 1", 113241234L, true, "msg1", "user1"),
                new ChatLog("Message 2", 2141242L, false, "msg2", "user1"),
                new ChatLog("Message 3", 313245L, true, "msg3", "user2")
        );

        when(dummyData.getChatLogs()).thenReturn(chatLogs);


        mockMvc.perform(delete("/chatlogs/user1/msg1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted chat log with message ID msg1 for user user1"));
    }
}