package com.example.restservice.observer;

import com.example.restservice.model.News;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Clasă de serviciu pentru trimiterea de emailuri.
 */
@Setter
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * Constructor pentru EmailService.
     * @param javaMailSender Obiectul JavaMailSender pentru trimiterea emailurilor.
     */
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Trimite o notificare prin email pentru știrile adăugate.
     * @param to Adresa de email a destinatarului.
     * @param news Obiectul știre.
     * @param categoryName Numele categoriei știrii.
     * @throws MessagingException dacă apare o eroare la crearea sau trimiterea mesajului de email.
     */
    public void sendEmailNewsAdded(String to, News news, String categoryName) throws MessagingException {
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
