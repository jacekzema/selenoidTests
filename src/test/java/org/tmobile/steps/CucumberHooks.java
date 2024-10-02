package org.tmobile.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.ExtendWith;
import org.tmobile.helpers.Logger;

import static org.tmobile.helpers.AllureHelper.attachLogFile;
import static org.tmobile.helpers.AllureHelper.takeScreenshot;

@ExtendWith({TextReportExtension.class})
public class CucumberHooks {

    @Before
    public void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Logger.info("Test is starting");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot();
        }
        Logger.info("Test has stopped");
        attachLogFile();
    }

}
