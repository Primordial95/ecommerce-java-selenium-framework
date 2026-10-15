package com.automationexercise.reports;

import com.automationexercise.utils.DateUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Standard
 * One report per run: all tests write into the same file.
 * Safe under parallel runs, synchronized.
 */
public final class ExtentManager {
    private static ExtentReports extent;

    private ExtentManager() {

    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = DateUtils.getDateForReport();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-reports/report_" + timestamp + ".html"); //decides how the results are written out
            spark.config().setDocumentTitle("Ecommerce Store Report");
            spark.config().setReportName("Test Execution Report");
            spark.config().setTheme(Theme.STANDARD);
            // create the report and connect the report
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            extent.setSystemInfo("Headless", System.getProperty("headless", "false"));
        }
        return extent;
    }
}
