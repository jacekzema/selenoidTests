package org.tmobile.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.tmobile.api.ExchangeRateFetcher;
import org.tmobile.helpers.Logger;

import java.util.List;
import java.util.Map;

public class ExchangeRateSteps {

    @Given("Get exchange rates")
    public void fetchExchangeRates() {
        ExchangeRateFetcher.fetchExchangeRates();
        Logger.info("Response Code: " + ExchangeRateFetcher.getResponseCode());
    }

    @Then("Display rate for currency code {string}")
    public void displayRateByCode(String code) {
        List<Map<String, Object>> rates = ExchangeRateFetcher.getExchangeRates();
        rates.stream()
                .filter(rate -> rate.get("code").equals(code))
                .forEach(rate -> Logger.info("Rate for " + code + ": " + rate.get("mid")));
    }

    @Then("Display the exchange rate for the currency named {string}")
    public void displayRateByName(String name) {
        List<Map<String, Object>> rates = ExchangeRateFetcher.getExchangeRates();
        rates.stream()
                .filter(rate -> rate.get("currency").equals(name))
                .forEach(rate -> Logger.info("Rate for " + name + ": " + rate.get("mid")));
    }

    @Then("Display currencies with rates above {float}")
    public void displayRatesAbove(float threshold) {
        List<Map<String, Object>> rates = ExchangeRateFetcher.getExchangeRates();
        rates.stream()
                .filter(rate -> (float) rate.get("mid") > threshold)
                .forEach(rate -> Logger.info("Currency: " + rate.get("currency") + ", Rate: " + rate.get("mid")));
    }

    @Then("Display currencies with rates below {float}")
    public void displayRatesBelow(float threshold) {
        List<Map<String, Object>> rates = ExchangeRateFetcher.getExchangeRates();
        rates.stream()
                .filter(rate -> (float) rate.get("mid") < threshold)
                .forEach(rate -> Logger.info("Currency: " + rate.get("currency") + ", Rate: " + rate.get("mid")));
    }
}
