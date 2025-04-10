package com.fiipractic.DTO;

public class UserProfileDTO {
    private Long id;
    private String email;
    private Boolean emailNotification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public UserProfileDTO(Long id, String email, Boolean emailNotification) {
        this.id = id;
        this.email = email;
        this.emailNotification = emailNotification;
    }
}
