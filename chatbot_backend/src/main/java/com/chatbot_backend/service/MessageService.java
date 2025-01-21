package com.chatbot_backend.service;



import java.util.List;

import com.chatbot_backend.model.Message;

public interface MessageService {
    Message saveMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(Long id);
}