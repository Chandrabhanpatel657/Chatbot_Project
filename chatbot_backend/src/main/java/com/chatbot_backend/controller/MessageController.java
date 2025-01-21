package com.chatbot_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot_backend.model.Message;
import com.chatbot_backend.service.MessageService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public MessageResponse receiveMessage(@RequestBody Message message) {
        message.setSender("user");
        messageService.saveMessage(message);

        // Simple response logic
        String botReply = "You said: " + message.getText();
        Message botMessage = new Message();
        botMessage.setText(botReply);
        botMessage.setSender("bot");
        messageService.saveMessage(botMessage);

        return new MessageResponse(botReply);
    }

    static class MessageResponse {
        private String reply;

        public MessageResponse(String reply) {
            this.reply = reply;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}