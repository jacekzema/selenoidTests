package org.tmobile.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AllureHelper {

    private static final String LOG_FILE_PATH = "logs/test-log.log";
    
    static public void attachLogFile() {
        File logFile = new File(LOG_FILE_PATH);
        try (FileInputStream fis = new FileInputStream(logFile)) {
            Allure.addAttachment("Test Log", "text/plain", fis, "log");
        } catch (FileNotFoundException e) {
            Logger.info("Log file not found: " + e.getMessage());
        } catch (Exception e) {
            Logger.info("Error attaching log file: " + e.getMessage());
        }
    }

    static public void takeScreenshot() {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
    }
}
