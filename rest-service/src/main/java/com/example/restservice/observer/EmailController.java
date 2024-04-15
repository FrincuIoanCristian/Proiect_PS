// Java Program to Create Rest Controller that
// Defines various API for Sending Mail

package com.example.restservice.observer;

// Importing required classes
import com.example.restservice.observer.EmailDetails;
import com.example.restservice.observer.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailSender emailService;
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

}
