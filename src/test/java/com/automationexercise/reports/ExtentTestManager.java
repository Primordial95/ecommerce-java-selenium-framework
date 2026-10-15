package com.automationexercise.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {
    //Why ThreadLocal, so that fail test cases don't mix into another report when running parallelly, as each thread
    //will have their own variable copy.
    private static final ThreadLocal<ExtentTest> CURRENT = new ThreadLocal<>();

    private ExtentTestManager() {

    }

    public static void set(ExtentTest test) {
        CURRENT.set(test);
    }

    public static ExtentTest get() {
        return CURRENT.get();
    }

    public static void remove() {
        CURRENT.remove();
    }


}
