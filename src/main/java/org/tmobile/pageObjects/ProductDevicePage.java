package org.tmobile.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.tmobile.helpers.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class ProductDevicePage {

    private final SelenideElement priceInfoWrapper = $x("//./main/section/section/div/span/div/div[2]/div/div/div");
    private final SelenideElement startPriceWrapper = priceInfoWrapper.$x("./div[1]/div[1]/div/div[2]/div/div");
    private final SelenideElement monthlyPriceWrapper = priceInfoWrapper.$x("./div[1]/div[2]/div/div/div[2]/div/div");
    private final SelenideElement addToCartButton = priceInfoWrapper.$("button[data-qa='PRD_AddToBasket']");

    public void checkIfProductDevicePageOpen() {
        Logger.info("Checking if product device page is opened");
        priceInfoWrapper.shouldBe(Condition.visible);
    }

    public String getStartPrice() {
        Logger.info("Getting start price");
        return startPriceWrapper.getText();
    }

    public String getMonthlyPrice() {
        Logger.info("Getting monthly price");
        return monthlyPriceWrapper.getText();
    }

    public void clickAddToCart() {
        Logger.info("Clicking add to cart button");
        addToCartButton.shouldBe(Condition.visible, Condition.enabled).click();
    }


}
