package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.converter.MessageEntityConverter;
import com.example.springsecurityjwt.entity.MessageEntity;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.entity.request.MessageRequest;
import com.example.springsecurityjwt.entity.response.MessageResponse;
import com.example.springsecurityjwt.service.MessageService;
import com.example.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
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

    @PostMapping("/message")
    @ResponseBody
    public List<MessageResponse> addMessage(@RequestBody @Valid MessageRequest messageRequest) {
        List<MessageResponse> result = new ArrayList<>();
        if (messageRequest.getMessage().startsWith("history ")){
            int value = Integer.parseInt(messageRequest.getMessage().replaceAll("[^0-9]+", ""));
            return messageService.getAllMessage()
                    .stream()
                    .sorted(Comparator.comparing(MessageEntity::getId).reversed())
                    .limit(value)
                    .map(converter::convertToMessageResponse)
                    .collect(Collectors.toList());
        }
        UserEntity user = userService.findByLogin(messageRequest.getLogin());
        MessageEntity messageEntity = messageService.saveMessage(user, messageRequest.getMessage());
        result.add(converter.convertToMessageResponse(messageEntity));
        return result;
    }
}
