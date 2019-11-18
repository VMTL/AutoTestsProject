# Java Spring Boot CRUD with test automation

This is a sample spring boot CRUD application with API tests

## Tools:

Maven
Spring boot
H2 database
Lombok
TestNG
RestAssured
Cucumber-JVM4
Selenium Webdriver
Allure Reports 2

Includes sequential test runs in a cross-browser environment via TestNG runner with Cucumber.
In Cucumber tests browsers parameters are passed through Scenario Outline, in TestNG browsers are passed from command line from Maven

## Runnig the application:
```
mvn spring-boot:run
```

## Runnig API tests while app is listening:
```
mvn test -Dsurefire.suiteXmlFiles=testng.xml
mvn test -Dsurefire.suiteXmlFiles=testngSoap.xml
```

## Runnig UI tests:
```
mvn test -Dsurefire.suiteXmlFiles=testngUI.xml -DbrowsersMvn=Chrome,FF
```

## Reporting:
Allure Reports results directory is set to Jenkins project workspace, but can be changed to /target
