# rest-api-automation-testing-framework
REST API - Test Automation Framework. The API tests have been divided into following categories:
	-->API Integration Tests
	-->Error Codes Tests
	-->Individual endpoint tests:
			-->AdditionServiceTest
			-->MultiplyServiceTest
			-->DivisionServiceTest
			-->MaxMinServicesTest
			-->UnionIntersectServiceTest

## Dependencies (pom.xml)

1. httpclient
2. httpcore
3. Hamcrest
4. TestNG


## How to run?

This test framework is developed to run against math-service local deployemnt (port 8080). Please refer to the application.properties file under config to know more details.

 - Run as TestNG
 - Run as Maven Install
 
## Bug Report

When the project is run as TestNG, it creates index.html and emailable-report.html under test-output folders. These reports can be referred as-is, or can be configured in Jenkins build to be sent as email.


## Bug report from last run (Sunday night):
Emailable report: https://s3.amazonaws.com/math-service-bug-report/emailable-report.html
Index.html report (TestNG): https://s3.amazonaws.com/math-service-bug-report/index.html




--> Check the application.properties file and make appropriate changes

## Additional References ##
https://mohitkaushik1987.atlassian.net/secure/RapidBoard.jspa?rapidView=1

Login Details:
abc@gmail.com
math-service-2018

