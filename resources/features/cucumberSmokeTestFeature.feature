@currentProjectTest
Feature: Example of cucumber test running from TestNG suite

  @negativeMessageScenario
  Scenario Outline: Entering negative values in the fields to check pop-up messaging
    Given Opening a "<browserName>" browser
    When Entering search_field "<searchField>" select_search "<searchList>"
    Then Verify results container is not empty

    Examples: 
      | browserName | searchField | searchList |
      | Chrome      | JeanCoutu | indice      | 
      | Chrome      | JeanCoutu | fund      | 
      | Chrome      | JeanCoutu | currency      | 
      | FireFox      | JeanCoutu | indice      | 
      | FireFox      | JeanCoutu | fund      | 
      | FireFox      | JeanCoutu | currency      | 
	  