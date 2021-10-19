package com.example.springsecurityjwt.converter;

import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.response.MessageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MessageEntityConverter {

    public MessageResponseDto convertToMessageResponse(MessageEntity messageEntity) {
        return new MessageResponseDto(messageEntity.getId(), messageEntity.getText(),
                messageEntity.getAuthor().getLogin());
    }
}
