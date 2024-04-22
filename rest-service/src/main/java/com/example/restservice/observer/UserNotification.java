package com.example.restservice.observer;

import com.example.restservice.model.Category;
import com.example.restservice.model.News;
import com.example.restservice.model.User;
import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.Setter;

/**
 * Clasa care implementează observatorul pentru notificările utilizatorului.
 */
public class UserNotification implements UserNewsObserver {

    @Setter
    @Getter
    private User user;

    private final EmailService emailService;

    /**
     * Constructor pentru UserNotification.
     * @param user Utilizatorul pentru care se creează notificatorul.
     * @param emailService Serviciul pentru trimiterea de emailuri.
     */
    public UserNotification(User user, EmailService emailService) {
        this.user = user;
        this.emailService = emailService;
    }

    /**
     * Metodă pentru actualizarea utilizatorului cu știrile noi.
     * @param news Știrea nouă.
     * @param category Categoria știrii noi.
     */
    @Override
    public void update(News news, Category category) {
        try {
            emailService.sendEmailNewsAdded(user.getEmail(), news, category.getCategoryName());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
