package com.example.restservice.model;
import com.example.restservice.observer.EmailDetails;
import com.example.restservice.observer.EmailSender;
import com.example.restservice.observer.NewsObserver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care reprezintă un utilizator în sistem.
 */
@Entity
public class User implements NewsObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "balance")
    private Double balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Subscription> subscriptions = new ArrayList<>();

    public User() {}

    public User(String username, String password, String email, String fullName, String avatar, Double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", balance=" + balance +
                ", subscriptions=" + subscriptions +
                '}';
    }

    /**
     * Metoda de notificare a utilizatorului despre o știre nouă.
     *
     * @param news Știrea nouă
     */
    @Override
    public void update(News news) {
        System.out.println(this.username + " stire noua: " + news.getTitle());
        EmailSender emailSender = new EmailSender();
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Stire noua");
        emailDetails.setMsgBody(news.getTitle());
        emailDetails.setRecipient(this.email);
        emailSender.sendSimpleMail(emailDetails);
    }
}
