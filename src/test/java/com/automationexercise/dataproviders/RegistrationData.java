package com.automationexercise.dataproviders;

import org.testng.annotations.DataProvider;

public class RegistrationData {

    @DataProvider
    public static Object[][] getValidCredentials(){
        Object[][] obj = new Object[1][2];
        obj[0][0] = "helloworld@demo.com";
        obj[0][1] = "helloworld";
        return obj;
    }
}
