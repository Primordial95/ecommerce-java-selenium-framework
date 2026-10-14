package com.automationexercise.utils;

import com.automationexercise.models.CredentialsData;


public class TestDataLoader {
    private static final String credentialFile = "/testdata/logindata.json";

    public static CredentialsData getCredentials() {
        return JsonReader.read(credentialFile, CredentialsData.class);
    }
}
