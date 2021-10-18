package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageEntity saveMessage(UserEntity author, String message){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(message);
        messageEntity.setAuthor(author);
        return messageRepository.save(messageEntity);
    }

    public List<MessageEntity> getAllMessage() {
        return messageRepository.findAll();
    }
}
