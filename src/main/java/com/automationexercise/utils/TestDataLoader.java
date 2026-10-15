package com.automationexercise.utils;

import com.automationexercise.models.CredentialsData;

import java.util.List;
import java.util.Map;


public final class TestDataLoader {
    private static final String LOGIN_FILE = "/testdata/logindata.json";
    private static final String REGISTRATION_FILE = "/testdata/registrationdata.xlsx";
    private static final String REGISTRATION_SHEET = "RegistrationData";

    private TestDataLoader(){
        //utility class
    }

    public static CredentialsData getCredentials() {
        return JsonReader.read(LOGIN_FILE, CredentialsData.class);
    }

    public static List<Map<String, String>> getRegistrationData(){
        return ExcelReader.readSheet(REGISTRATION_FILE,REGISTRATION_SHEET);
    }
}
