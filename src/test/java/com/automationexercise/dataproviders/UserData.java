package com.automationexercise.dataproviders;

import com.automationexercise.utils.TestDataLoader;
import org.testng.annotations.DataProvider;

public class UserData {
    private UserData(){
        //Utility Class
    }
    @DataProvider(name = "validCredentials")
    public static Object[][] ValidCredentials(){
        return TestDataLoader.getCredentials().getValidCredentials().stream()
                .map(c -> new Object[]{c}).toArray(Object[][]::new);
    }

    @DataProvider(name= "invalidCredentials")
    public static Object[][] invalidCredentials(){
        return TestDataLoader.getCredentials().getInvalidCredentials().stream()
                .map(c -> new Object[]{c}).toArray(Object[][]::new);
    }

    @DataProvider(name = "RegistrationData", parallel = true)
    public static Object[][] registrationDetails(){
        return TestDataLoader.getRegistrationData().stream().map(c -> new Object[]{c})
                .toArray(Object[][]::new);
    }
}
