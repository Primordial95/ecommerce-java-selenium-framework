package com.automationexercise.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static int count = 0;
    private static final int MAX_TRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_TRY) {
            count++;
            return true;
        }
        return false;
    }
}
