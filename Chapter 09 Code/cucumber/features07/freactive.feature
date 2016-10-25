Feature: My First Cucumber
  Meeting people and counting ...

  Scenario: Meeting people
  	Given It is a new day
    Given I met Niko
    Given I met Erik
    Given I met Dave
    Then I have met 3 people and the last person was Dave

  Scenario: Meeting people again
    Given It is a new day
    Given I meet those people:
    	| Niko |
    	| Dave |
    	| Erik |
    Then I have met 3 people and the last person was Erik