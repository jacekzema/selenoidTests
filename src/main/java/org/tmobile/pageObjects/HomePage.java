package org.tmobile.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.tmobile.helpers.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final String HOME_PAGE_URL = "https://www.t-mobile.pl/";

    private final SelenideElement cookieBanner = $("div[data-testid='notice']");
    private final SelenideElement cookieAcceptButton = cookieBanner.$("button[id='didomi-notice-agree-button']");
    private final SelenideElement topNavigationMenu = $("nav[id='main-menu']");
    private final SelenideElement devicesMenuButton = topNavigationMenu.$x("./div/ul/li[1]");
    private final SelenideElement devicesSubMenu = topNavigationMenu.$x("./div/ul/li[1]/div");
    private final SelenideElement noSubscriptionList = devicesMenuButton.$x("./div/div/ul/li[4]");
    private final SelenideElement smartwatchLink = noSubscriptionList.$x("./ul/li[2]/a");
    private final SelenideElement basketMenuIcon = $x("//./header/div/div[1]/div/div/a[1]");
    private final SelenideElement numberOfItemsInBasket = basketMenuIcon.$x("./div");

    private final Duration WAIT_3_SECONDS = Duration.ofSeconds(3);
    private final Duration WAIT_1_SECONDS = Duration.ofSeconds(1);

    public void openHomePage() {
        Logger.info("Opening home page: " + HOME_PAGE_URL);
        open(HOME_PAGE_URL);
    }

    public void checkIfHomePageOpen() {
        Logger.info("Checking if home page is opened");
        topNavigationMenu.shouldBe(Condition.visible.because("The main menu should be visible on the home page"));
    }

    public String getTitle() {
        Logger.info("Getting page title");
        String title = Selenide.title();
        Logger.info("Page title: " + title);
        return title;
    }

    public void acceptCookiesIfVisible() {
        startCookieWatcher();
    }

    public void hoverOverDevicesMenu() {
        Logger.info("Hovering over devices menu");
        devicesMenuButton.shouldBe(Condition.visible, Condition.enabled).hover();
    }

    public void checkIfDevicesMenuVisible() {
        Logger.info("Checking if devices menu is visible");
        devicesSubMenu.shouldBe(Condition.visible);
    }

    public void clickSmartwatchLink() {
        Logger.info("Clicking smartwatch link using JavaScript click as a fallback");
        smartwatchLink.shouldBe(Condition.visible, Condition.enabled)
                .scrollIntoView(true);
        executeJavaScript("arguments[0].click();", smartwatchLink);
    }

    public String getNumberOfItemsInBasket() {
        Logger.info("Getting number of items in basket");
        return numberOfItemsInBasket.shouldBe(Condition.visible, Condition.enabled).getText();
    }


    private void startCookieWatcher() {
        Logger.info("Checking for cookie banner visibility...");
        try {
            boolean cookieBannerAccepted = Selenide.Wait().withTimeout(WAIT_3_SECONDS)
                    .pollingEvery(WAIT_1_SECONDS)
                    .until((WebDriver wd) -> {
                        if (cookieBanner.isDisplayed()) {
                            Logger.info("Cookie banner detected. Accepting cookies.");
                            cookieAcceptButton.click();
                            return true;
                        }
                        return false;
                    });

            if (!cookieBannerAccepted) {
                Logger.info("Cookie banner not detected. Proceeding without accepting cookies.");
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            Logger.info("Cookie banner not detected within the timeout period. Proceeding without accepting cookies.");
        }
    }
}
