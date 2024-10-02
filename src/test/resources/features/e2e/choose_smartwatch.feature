@e2e
Feature: Choose device from the list

  Scenario: Choose smartwatch from the list
    Given Go to T-mobile.pl home page
    Then Home page is opened
    When Choose devices from top menu
    Then Devices menu is visible
    And Choose smartwatch from no subscription list
    Then No subscription devices page is opened
    And Select device 1 from list
    Then Device page is opened
    And Get start price and monthly price from device page
    And Add device to cart
    Then Basket page is open
    And Check if the prices are the same
    When Go to main page from basket page
    Then Home page is opened
    Then Check if the number of items in basket is "1"