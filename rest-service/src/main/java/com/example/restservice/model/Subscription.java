package com.example.restservice.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "active")
    private boolean active;

    public Subscription() {}

    public Subscription(User user, Category category, LocalDate startDate, Double amountPaid, boolean active) {
        this.user = user;
        this.category = category;
        this.startDate = startDate;
        this.amountPaid = amountPaid;
        this.active = active;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionId=" + subscriptionId +
                ", user=" + user +
                ", category=" + category +
                ", startDate=" + startDate +
                ", amountPaid=" + amountPaid +
                ", active=" + active +
                '}';
    }
}
