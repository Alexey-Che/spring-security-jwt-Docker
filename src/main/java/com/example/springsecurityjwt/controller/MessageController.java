package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.converter.MessageEntityConverter;
import com.example.springsecurityjwt.dto.request.MessageRequestDto;
import com.example.springsecurityjwt.dto.request.RegistrationRequestDto;
import com.example.springsecurityjwt.dto.response.MessageResponseDto;
import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.service.MessageService;
import com.example.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final MessageEntityConverter converter;

    @Autowired
    public MessageController(UserService userService, MessageService messageService, MessageEntityConverter converter) {
        this.userService = userService;
        this.messageService = messageService;
        this.converter = converter;
    }

    @PostMapping("/message") //сохранение сообщения от авторизованного пользователя, либо получение истории сообщений
    @ResponseBody
    public List<MessageResponseDto> addMessage(@RequestBody @Valid MessageRequestDto messageRequest) {
        List<MessageResponseDto> result = new ArrayList<>();
        if (messageRequest.getMessage().startsWith("history ")){
            int limit = Integer.parseInt(messageRequest.getMessage().replaceAll("[^0-9]+", ""));
            return messageService.getMessageHistory(limit)
                    .stream()
                    .map(converter::convertToMessageResponse)
                    .collect(Collectors.toList());
        }
        UserEntity user = userService.findByLogin(messageRequest.getLogin());
        MessageEntity messageEntity = messageService.saveMessage(user, messageRequest.getMessage());
        result.add(converter.convertToMessageResponse(messageEntity));
        return result;
    }
}
