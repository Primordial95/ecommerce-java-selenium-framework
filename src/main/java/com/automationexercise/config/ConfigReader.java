package com.automationexercise.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    private static String getConfigProperties(String key) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
        try (fis) {
            properties.load(fis);
        }
        return properties.getProperty(key);
    }

    private static String getqaProperties(String key) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/config/qa.properties");
        try (fis) {
            properties.load(fis);
        }
        return properties.getProperty(key);
    }

    public static String getBrowserName() throws IOException {
        return getConfigProperties("browser");
    }

    public static String getBaseUrl() throws IOException {
        return getqaProperties("baseurl");
    }


}
