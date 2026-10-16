package com.automationexercise.reports;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;

public final class ExtentLogAppender extends AbstractAppender {

    private static final String NAME = "ExtentAppender";

    private ExtentLogAppender() {
        super(NAME, null, PatternLayout.createDefaultLayout(), true, Property.EMPTY_ARRAY);
    }

    @Override
    public void append(LogEvent event) {
        ExtentTest test = ExtentTestManager.get();
        if (test == null) {
            return;
        }
        String message = event.getMessage().getFormattedMessage();
        if (event.getLevel().isMoreSpecificThan(Level.ERROR)) {
            test.warning("ERROR: " + message);
        } else if (event.getLevel() == Level.WARN) {
            test.warning(message);
        } else {
            test.info(message);
        }
    }

    public static synchronized void install() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        if (config.getAppender(NAME) != null) {
            return;
        }
        Appender appender = new ExtentLogAppender();
        appender.start();
        config.addAppender(appender);
        config.getLoggerConfig("com.automationexercise").addAppender(appender, Level.INFO, null);
        context.updateLoggers();
    }
}