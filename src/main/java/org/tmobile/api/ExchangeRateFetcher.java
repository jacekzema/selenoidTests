package org.tmobile.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class ExchangeRateFetcher {
    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/tables/A";
    private static List<Map<String, Object>> exchangeRates;
    private static int responseCode;

    public static void fetchExchangeRates() {
        Response response = RestAssured.get(API_URL);
        responseCode = response.getStatusCode();
        exchangeRates = response.jsonPath().getList("[0].rates");
    }

    public static List<Map<String, Object>> getExchangeRates() {
        return exchangeRates;
    }

    public static int getResponseCode() {
        return responseCode;
    }
}
