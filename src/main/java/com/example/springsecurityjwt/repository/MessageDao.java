package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.MessageEntity;

import java.util.List;

public interface MessageDao {

    List<MessageEntity> getMessageHistory(int limit);
}
