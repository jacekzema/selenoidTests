# Selenoid & rest-assured test framework

## All tests

All tests are run by default in head mode and in Chrome browser

`mvn clean test`

## Run tests in headless mode (true/false)

`mvn clean test -Dheadless=true`

## Run tests in different browsers (chrome/firefox)

`mvn clean test -Dbrowser=chrome`

## Generate report

`mvn allure:report`

## Run generated report

`mvn allure:serve`

## Logs file is located in

`./logs/test-log.log`

## API tests

`mvn clean test -Papi-tests`

or

`mvn clean test -D"cucumber.filter.tags=@api"`

## E2E tests

`mvn clean test -Pe2e-tests`

or

`mvn clean test -D"cucumber.filter.tags=@e2e"`