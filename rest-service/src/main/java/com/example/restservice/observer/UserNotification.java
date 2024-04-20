package com.example.restservice.observer;

import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.Setter;


public class UserNotification implements UserNewsObserver {

    @Setter
    @Getter
    private User user;

    private final EmailService emailService;

    public UserNotification(User user, EmailService emailService) {
        this.user = user;
        this.emailService = emailService;
    }

    @Override
    public void update(News news, Category category) {
        System.out.println("###################################################################################");
        System.out.println("Sending email to: " + user.getEmail());
        try {
            emailService.sendEmailNewsAdded(user.getEmail(), news, category.getCategoryName());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email was sent");
        System.out.println("###################################################################################\n");
    }
}
