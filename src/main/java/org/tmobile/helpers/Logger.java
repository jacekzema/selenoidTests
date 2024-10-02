package org.tmobile.helpers;

import io.qameta.allure.Allure;
import org.slf4j.LoggerFactory;

public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
        Allure.step(message);
    }

    public static void error(String message, Exception e) {
        logger.error(message, e);
        Allure.step(message);
    }
}
