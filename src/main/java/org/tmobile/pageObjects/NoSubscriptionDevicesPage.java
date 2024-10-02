package org.tmobile.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.tmobile.helpers.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NoSubscriptionDevicesPage {

    private final SelenideElement deviceListHeader = $("div[id='device-list-header']");
    private final SelenideElement deviceList = $x("//./main/div[3]/div/div[1]/div/div[2]/div[2]");

    public void checkIfNoSubscriptionDevicesPageOpen() {
        Logger.info("Checking if no subscription devices page is opened");
        deviceListHeader.shouldBe(Condition.visible);
    }

    public void selectDevice(int deviceIndex) {
        Logger.info("Selecting device with index: " + deviceIndex);
        deviceList.$x("./div[" + deviceIndex + "]").shouldBe(Condition.visible, Condition.enabled).click();
    }


}
