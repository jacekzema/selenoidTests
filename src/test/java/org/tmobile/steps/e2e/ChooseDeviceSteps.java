package org.tmobile.steps.e2e;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.tmobile.pageObjects.BasketPage;
import org.tmobile.pageObjects.HomePage;
import org.tmobile.pageObjects.NoSubscriptionDevicesPage;
import org.tmobile.pageObjects.ProductDevicePage;

public class ChooseDeviceSteps {
    private final String HOME_PAGE_TITLE = "T-Mobile";

    HomePage homePage = new HomePage();
    NoSubscriptionDevicesPage noSubscriptionDevicesPage = new NoSubscriptionDevicesPage();
    ProductDevicePage productDevicePage = new ProductDevicePage();
    BasketPage basketPage = new BasketPage();

    private String startPriceFromDevicePage;
    private String monthlyPriceFromDevicePage;

    @Given("Go to T-mobile.pl home page")
    public void openBrowser() {
        homePage.openHomePage();
        homePage.acceptCookiesIfVisible();
    }

    @Then("Home page is opened")
    public void homePageIsOpened() {
        homePage.checkIfHomePageOpen();
        Assertions.assertTrue(homePage.getTitle().contains(HOME_PAGE_TITLE));
    }

    @When("Choose devices from top menu")
    public void chooseDevicesFromTopMenu() {
        homePage.hoverOverDevicesMenu();
    }

    @Then("Devices menu is visible")
    public void devicesMenuIsVisible() {
        homePage.checkIfDevicesMenuVisible();
    }

    @When("Choose smartwatch from no subscription list")
    public void chooseSmartwatchFromNoSubscriptionList() {
        homePage.clickSmartwatchLink();
    }

    @Then("No subscription devices page is opened")
    public void noSubscriptionDevicesPageIsOpened() {
        noSubscriptionDevicesPage.checkIfNoSubscriptionDevicesPageOpen();
    }

    @When("Select device {int} from list")
    public void selectDeviceFromList(int deviceIndex) {
        noSubscriptionDevicesPage.selectDevice(deviceIndex);
    }

    @Then("Device page is opened")
    public void devicePageIsOpened() {
        productDevicePage.checkIfProductDevicePageOpen();
    }

    @And("Get start price and monthly price from device page")
    public void getStartPriceFromDevicePage() {
        startPriceFromDevicePage = productDevicePage.getStartPrice();
        monthlyPriceFromDevicePage = productDevicePage.getMonthlyPrice();
    }

    @When("Add device to cart")
    public void addDeviceToCart() {
        productDevicePage.clickAddToCart();
    }


    @Then("Basket page is open")
    public void baskedPageIsOpened() {
        basketPage.checkIfBasketPageOpen();
    }

    @And("Check if the prices are the same")
    public void checkIfPricesAreTheSame() {
        String startPriceSummary = basketPage.getStartPriceSummary();
        String monthlyPriceSummary = basketPage.getMonthPriceSummary();
        Assertions.assertEquals(startPriceFromDevicePage, startPriceSummary);
        Assertions.assertEquals(monthlyPriceFromDevicePage, monthlyPriceSummary);
    }

    @When("Go to main page from basket page")
    public void goToMainPageFromBasketPage() {
        basketPage.goToMainPage();
    }

    @Then("Check if the number of items in basket is {string}")
    public void checkIfTheNumberOfItemsInBasketIs(String expectedNumberOfItemsInBasket) {
        String numberOfItemsInBasket = homePage.getNumberOfItemsInBasket();
        Assertions.assertEquals(expectedNumberOfItemsInBasket, numberOfItemsInBasket);
    }

}
