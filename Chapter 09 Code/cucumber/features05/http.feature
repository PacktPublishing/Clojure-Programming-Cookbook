Feature: My First Cucumber
  HTTP and Mongo. Retrieve weather info from HTTP 
  and compute average using MongoDB

  Scenario: Storing Data For Cities
  When I load weather data for the following cities:
  	| Tokyo 	 |
  	| Osaka 	 |
  	| Kobe  	 |
  	| Fukuoka	 | 
  	| Sapporo  |
  	| Nagano 	 |
  	| Nara		 |
  	| Yokohama |
  Then I expect the average to be lower than 30