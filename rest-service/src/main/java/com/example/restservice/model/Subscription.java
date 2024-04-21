package com.example.restservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
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

    public Subscription() {
    }

    public Subscription(long subscriptionId, User user, Category category, LocalDate startDate, double amountPaid) {
        this.subscriptionId = subscriptionId;
        this.user = user;
        this.category = category;
        this.startDate = startDate;
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return subscriptionId == that.subscriptionId && Objects.equals(user, that.user) && Objects.equals(category, that.category) && Objects.equals(startDate, that.startDate) && Objects.equals(amountPaid, that.amountPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, user, category, startDate, amountPaid);
    }
}
