package org.tmobile.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.tmobile.helpers.Logger;

import static com.codeborne.selenide.Selenide.*;

public class BasketPage {

    private final SelenideElement basketSummary = $("div[id='basketSummary']");
    private final SelenideElement startPriceSummary = basketSummary.$("div[data-qa='BKT_TotalupFrontCurrCOde']");
    private final SelenideElement monthPriceSummary = basketSummary.$("div[data-qa='BKT_TotalMonthlyCurrCOde']");
    private final SelenideElement leftLogoWrapper = $x("//*[@id='osAppInnerContainer']/div[2]/div[1]/a");

    public void checkIfBasketPageOpen() {
        Logger.info("Checking if basket page is opened");
        basketSummary.shouldBe(Condition.visible);
    }

    public String getStartPriceSummary() {
        Logger.info("Getting start price summary");
        return startPriceSummary.getText();
    }

    public String getMonthPriceSummary() {
        Logger.info("Getting month price summary");
        return monthPriceSummary.getText();
    }

    public void goToMainPage() {
        Logger.info("Going to main page link using JavaScript click as a fallback");
        leftLogoWrapper.shouldBe(Condition.visible, Condition.enabled)
                .scrollIntoView(true);
        executeJavaScript("arguments[0].click();", leftLogoWrapper);
    }


}
