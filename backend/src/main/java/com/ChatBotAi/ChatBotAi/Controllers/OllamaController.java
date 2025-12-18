package com.ChatBotAi.ChatBotAi.Controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama")
// @CrossOrigin("")
public class OllamaController {
    
    private ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel)
    {
        chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{input}")
    public ResponseEntity<String> respond(@PathVariable String input)
    {
        ChatResponse chatResponse = chatClient.prompt(input).call().chatResponse(); // instead of just chatmessage.call() to get more options, more abstraction
        String output = chatResponse.getResult().getOutput().getText();
        return ResponseEntity.ok(output);
    }
}
