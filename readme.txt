This file is intended to help users to execute the security-module-1.0.jar for validating hash(es), 
which is used to test the user's key passed through a REST service which is available after deploying the above jar using spring boot.

Pre-requisites: 
Java 1.7 or higher
Windows OS
maven

Steps to run the executable jar:

1. Initially clone the url https://github.com/ashinrobi/security-module.git to any Windows directory of your choice
2. Using maven build the project.
3. Using command line, change directory to <git_directory>/security-module/target
4. Now execute the command, java -jar security-module-1.0.jar

Steps to run the REST service:

1. Having deployed the authentication service through the above steps, kindly start any rest client of your choice.
2. Kindly input/select the following in places where prompted:
	a. URL: http://localhost:8080/security/validateUser
	b. method: POST
	c. content-type: application/json
	d. raw input sample: 
		{
		  "name":"Ashin Wilson", 
		  "email":"ashin.robi@gmail.com", 
		  "address":"INDIA", 
		  "responseSignature":"ohlIxBJTdE9mXPoSZ5Lq15T74EngJ3hAGZe+Hv14sEQ="
		}
3. Send this request through the client. The response will be:
    a. Http Status code of 200 (Success) for valid users.
    b. Http Status code of 401 (Unauthorized) for invalid users.
    
