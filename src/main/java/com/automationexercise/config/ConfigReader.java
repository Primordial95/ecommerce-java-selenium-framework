package com.automationexercise.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * We are using Static block here to intialise these static variable, so that the file will be loaded once, instead of
 * using the intialization everytime. Better Performance.
 */
public class ConfigReader {
    private static final Properties qaProperties = new Properties();
    private static final Properties configProperties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");) {
            configProperties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load/read configuration file", e);
        }
    }

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/qa.properties");) {
            qaProperties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load/read configuration file", e);
        }
    }

    private static String getConfigProperties(String key) {
        return configProperties.getProperty(key);
    }

    private static String getQaProperties(String key) {
        return qaProperties.getProperty(key);
    }

    public static String getBrowserName() {
        return getConfigProperties("browser");
    }

    public static String getBaseUrl() {
        return getQaProperties("baseurl");
    }

    public static int getTimeout() {
        return Integer.parseInt(getConfigProperties("timeout"));
    }


}
