Feature: Cuking with csv
  I want to see how to cuke around with csv

  Scenario: Reading a table
	Given data from file "set1.csv":
  	Then the total should be 350.

  Scenario: Same with property from command line
  	Given data from environment	
  	Then the total should be 350.