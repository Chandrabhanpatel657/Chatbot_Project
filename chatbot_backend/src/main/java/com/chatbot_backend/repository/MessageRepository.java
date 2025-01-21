package com.chatbot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatbot_backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
