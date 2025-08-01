package com.example.happy_travel.models;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String title;

    @Column(nullable = false, length = 30)
    private String country;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Destination() {
    }

    public Destination(String title, String country, String city, String image, String description, User user) {
        this.title = title;
        this.country = country;
        this.city = city;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    public Destination(String title, String country, String city, String image, String description) {
        this.title = title;
        this.country = country;
        this.city = city;
        this.image = image;
        this.description = description;
    }

    public Destination(Long id, String title, String country, String city, String image, String description, User user) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.city = city;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

