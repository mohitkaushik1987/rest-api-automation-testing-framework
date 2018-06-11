# math-service: rest-api-automation-testing-framework

REST API - Test Automation Framework. The API tests have been divided into following categories:
	-API Integration Tests
	-Error Codes Tests
	-Individual endpoint tests:
		-AdditionServiceTest
		-MultiplyServiceTest
		-DivisionServiceTest
		-MaxMinServicesTest
		-UnionIntersectServiceTest

## Dependencies (pom.xml)

1. httpclient
2. httpcore
3. Hamcrest
4. TestNG
5. json
6. jackson-databind


## How to run the test project?

This test framework is developed to run against math-service local deployemnt (port 8080). Please refer to the application.properties file under config to know more details.

 *Step 1 -  Run as Maven clean (Build Success)*
 
 *Step 2 -  Run as TestNG (Will show the Status: Tests run, Failures, Skips)*
 
 **OR** *-   Run as Maven install (BUILD FAILURE, because some tests will fail - as expected)*
 
 
## Bug Report

At mvn install, the *surefire-plugin* generates the bug reports (emailable-report.hrml and index.html). 

These surefire-reports are generated under *rest-api-automation-testing-framework/target/surefire-reports/* folder. These reports can be referred as-is, or can be configured in Jenkins (CD/CI) build to be sent as email.

The Project can also be run as *run as TestNG*, it creates index.html and emailable-report.html under test-output folders. 

**Please refer the sample bug report from last run, shared on AWS S3 for ready reference.**


## Bug report from last run (Sunday night):

**Emailable report:** https://s3.amazonaws.com/math-service-bug-report/emailable-report.html

**Index.html report (TestNG):** https://s3.amazonaws.com/math-service-bug-report/index.html





## Additional References ##
https://mohitkaushik1987.atlassian.net/secure/RapidBoard.jspa?rapidView=1

Login Details:
abc@gmail.com
math-service-2018

