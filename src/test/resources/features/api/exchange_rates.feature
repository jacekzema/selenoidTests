@api
Feature: Exchange Rates

  Scenario Outline: Exchange rates
    Given Get exchange rates
    Then Display rate for currency code "<currencyCode>"
    And Display the exchange rate for the currency named "<currencyName>"
    And Display currencies with rates above <rateAbove>
    And Display currencies with rates below <rateBelow>

    Examples:
      | currencyCode | currencyName      | rateAbove | rateBelow |
      | USD          | dolar amerykański | 5         | 3         |
      | EUR          | euro              | 4         | 2         |
      | GBP          | funt szterling    | 6         | 4         |