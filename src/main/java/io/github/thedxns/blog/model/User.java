package io.github.thedxns.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "The username must not be empty")
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @NotBlank(message = "The password must not be empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "The name of the user must not be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "The surname of the user must not be empty")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "The e-mail address of the user must not be empty")
    @Column(name = "email")
    private String email;

    @Column(name = "subscriber_status")
    private boolean subscriberStatus;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscriberStatus() {
        return subscriberStatus;
    }

    public void setSubscriberStatus(boolean subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }
}