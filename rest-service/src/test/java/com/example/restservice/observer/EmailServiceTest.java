package com.example.restservice.observer;

import com.example.restservice.model.News;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Teste pentru clasa EmailService.
 */
public class EmailServiceTest {
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private MimeMessage mimeMessage;
    private EmailService emailService;

    /**
     * Initializarea testelor
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emailService = new EmailServiceImpl(mailSender);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        ((EmailServiceImpl) emailService).setSender("sender@example.com");
    }

    /**
     * Test pentru metoda sendEmailNewsAdded
     * @throws MessagingException aceast metoda poate arunca o exceptie
     */
    @Test
    public void sendEmailNewsAddedTest() throws MessagingException {
        News news = new News();
        news.setTitle("Title");
        news.setPublishedAt(LocalDate.now());
        String categoryName = "Category";
        String to = "recipient@example.com";
        emailService.sendEmailNewsAdded(to, news, categoryName);
        verify(mailSender).createMimeMessage();
        verify(mailSender).send(mimeMessage);
    }
}
