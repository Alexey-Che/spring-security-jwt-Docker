package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public MessageDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<MessageEntity> getMessageHistory(int limit) {
        return entityManager.createNativeQuery("SELECT * FROM message_table ORDER BY id DESC LIMIT :limit",
                MessageEntity.class).setParameter("limit", limit).getResultList();
    }
}
