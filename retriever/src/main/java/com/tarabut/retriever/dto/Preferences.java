package com.tarabut.retriever.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Preferences {

    @Id
    @GeneratedValue
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

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public void setPost(Boolean post) {
        this.post = post;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }
}
