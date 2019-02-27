# Java test automation

This is a sample test automation project, covering UI and API tests

## Tools:

Maven
TestNG
Cucumber-JVM4
Selenium Webdriver
RestAssured
Allure Reports 2

Includes sequential test runs in a cross-browser environment via TestNG runner with Cucumber.
In Cucumber tests browsers parameters are passed thorugh Scnario Outline, in TestNG browsers are passed from command line from Maven

## Run:
```
mvn clean test -DbrowsersMvn=Chrome,FF
```

## Reporting:
Allure Reports results directoy is set to Jenkins project workspace, but can be changed to /target
