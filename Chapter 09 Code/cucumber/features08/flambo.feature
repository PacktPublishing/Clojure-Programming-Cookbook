Feature: Flambing
	This is a great way to put some notes about Flambing

	Scenario: Word analysis in from a text file 
		Given the following text file: "fixtures/sherlock.txt"
		Given we count the number of lines using shell
		Then we wanna check the number of lines is the same