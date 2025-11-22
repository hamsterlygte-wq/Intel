package com.amigoscode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private ChatClient chatclient;


    public AiService(ChatClient.Builder builder) {
        chatclient = builder.build();
    }

    public String chat(String prompt){
        return chatclient
                .prompt(prompt)
                .call()
                .content();
    }

}
