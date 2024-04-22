package com.example.restservice.observer;

import com.example.restservice.model.News;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailNewsAdded(String to, News news, String categoryName) throws MessagingException;
}
