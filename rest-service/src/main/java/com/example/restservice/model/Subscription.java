package com.example.restservice.model;
import jakarta.persistence.*;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subscriptionId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private boolean notificationMethod;

    public Subscription(){}

    public Subscription(long subscriptionId, User user, Category category, boolean notificationMethod) {
        this.subscriptionId = subscriptionId;
        this.user = user;
        this.category = category;
        this.notificationMethod = notificationMethod;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(boolean notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
