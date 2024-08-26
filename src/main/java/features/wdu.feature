@RegressionTest
Feature: Webdriver University Ajax Loader

  Scenario: Verify Ajax Loader popup
    Given Navigate to "http://webdriveruniversity.com/index.html"
    Then Verify AJAX LOADER is present or not using Assertions
    And Click on AJAX LOADER link
    Then Click on Click Me button
    And verify the pop-up is present or not
    And Close the browser