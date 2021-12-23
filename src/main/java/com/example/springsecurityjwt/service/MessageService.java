package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.repository.MessageDao;
import com.example.springsecurityjwt.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageDao messageDao;

    public MessageEntity saveMessage(UserEntity author, String message){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(message);
        messageEntity.setAuthor(author);
        return messageRepository.save(messageEntity);
    }

    public List<MessageEntity> getMessageHistory(int limit) {
        return messageDao.getMessageHistory(limit);
    }
}
