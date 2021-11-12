package com.fin.cleardoxservice.model;

public class UserDetailsRequestModel {

    private String login_client_id;
    private String password;
    private String identifier;

    public UserDetailsRequestModel() {
    }

    public String getLogin_client_id() {
        return login_client_id;
    }

    public void setLogin_client_id(String login_client_id) {
        this.login_client_id = login_client_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
