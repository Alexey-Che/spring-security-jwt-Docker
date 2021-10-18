package com.example.springsecurityjwt.converter;

import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public class MessageEntityConverter {

    public MessageResponse convertToMessageResponse(MessageEntity messageEntity) {
        return new MessageResponse(messageEntity.getId(), messageEntity.getText(),
                messageEntity.getAuthor().getLogin());
    }
}
