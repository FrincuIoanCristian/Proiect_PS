package com.example.restservice.observer;

import com.example.restservice.model.News;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailNewsAdded(String to, News news, String categoryName) throws MessagingException {
        if ("youremail@email.com".equals(sender)) {
            System.out.println("Email not set up. Please configure the email in the application.properties file.\n");
            return;
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject("Stire noua in " + categoryName + "!!");

        String body = "<html><body>" +
                "<h1>" + news.getTitle() + "</h1>" +
                "<h2>Publicat la: " + news.getPublishedAt() + "</h2>" +
                "</body></html>";

        helper.setText(body, true);

        javaMailSender.send(message);
    }

}
