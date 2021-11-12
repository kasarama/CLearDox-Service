package com.fin.cleardoxservice.model;

import java.time.LocalDateTime;

public class TokenModel {
    private String token;
    private LocalDateTime expiryDateTime;

    public TokenModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}
