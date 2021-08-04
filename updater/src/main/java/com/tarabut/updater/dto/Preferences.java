package com.tarabut.updater.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_IDENTIFIER")
    private String userIdentifier;

    @Column(nullable = false)
    private Boolean sms;

    @Column(nullable = false)
    private Boolean post;

    @Column(nullable = false)
    private Boolean email;

    @Column
    private Timestamp created_at;

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public Boolean getSms() {
        return sms;
    }

    public Boolean getPost() {
        return post;
    }

    public Boolean getEmail() {
        return email;
    }

    public Preferences(String userIdentifier, Boolean sms, Boolean post, Boolean email) {
        this.userIdentifier = userIdentifier;
        this.sms = sms;
        this.post = post;
        this.email = email;
    }

    public Preferences() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preferences that = (Preferences) o;
        return Objects.equals(userIdentifier, that.userIdentifier) && Objects.equals(sms, that.sms) && Objects.equals(post, that.post) && Objects.equals(email, that.email) && Objects.equals(created_at, that.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdentifier, sms, post, email, created_at);
    }
}
