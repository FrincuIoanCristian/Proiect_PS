package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clasa care reprezinta entitatea "Category" in cadrul aplicatiei.
 * Aceasta clasa este mapata pe o tabela în baza de date si contine informatii despre categoriile aplicatiei.
 * Am folosit @Setter si @Getter pentru a nu mai scrie settere-le si gettere-le implicit
 */
@Setter
@Getter
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "subscription_cost")
    private Double subscriptionCost;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Subscription> subscriptions = new ArrayList<>();

    public Category() {
    }

    public Category(long categoryId, String categoryName, Double subscriptionCost) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subscriptionCost = subscriptionCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId && Objects.equals(categoryName, category.categoryName) && Objects.equals(subscriptionCost, category.subscriptionCost) && Objects.equals(newsList, category.newsList) && Objects.equals(subscriptions, category.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, subscriptionCost, newsList, subscriptions);
    }
}
