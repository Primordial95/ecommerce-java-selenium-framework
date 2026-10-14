package com.automationexercise.models;

import java.util.List;

public class CredentialsData {

    private List<Credential> validCredentials;
    private List<Credential> invalidCredentials;

    public List<Credential> getValidCredentials() {
        return validCredentials;
    }

    public void setValidCredentials(List<Credential> validCredentials) {
        this.validCredentials = validCredentials;
    }

    public List<Credential> getInvalidCredentials() {
        return invalidCredentials;
    }

    public void setInvalidCredentials(List<Credential> invalidCredentials) {
        this.invalidCredentials = invalidCredentials;
    }

}
