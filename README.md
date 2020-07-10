
	SignUp-Login Application	
	
	SignUp-Login Application is a application where user can sign in & track his login information.
	When user will sign up user will get welcome msg on his mail.and session is stored using redis.
	
	
	Getting  Started 
	
	These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
	See deployment for notes on how to deploy the project on a live system.


	Prerequisites

	Eclipse IDE for Enterprise Java Developers
	Redis
	pgAdmin
	PostGreSQL 10
	


	Technology  & Frameworks & Design Patterns
	
		Spring Boot
		Spring Security 
		Spring MVC
		DAO
		log4j
		Java
		JSP
	
		
Installing

	A step by step series of examples that tell you how to get a development env running
	
	
	For Downloading Eclipse Java EE Developers
	
	https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2020-03/R/eclipse-jee-2020-03-R-incubation-win32-x86_64.zip. 

	For Redis Use
	https://github.com/MSOpenTech/redis/releases
	
	
	For JBoss wildfly Server 
	https://wildfly.org/downloads/  -> look for -> Java EE Full & Web Distribution
	

	Steps for running the project using wildfly server

	Step 1  Install Wildfly server
	
	Step 2  Copy war file and paste it into	yourwildflyfolder\wildfly-20.0.0.Final\standalone\deployments	folder.
	
	Step 3  Open Terminal Or command prompt browse to the bin folder
	
	Step 4  To start wildfly run command  
		    standalone.bat -c standalone-full.xml 
			
		It will autometically start a server and also deployed war file on the server.
	
	Step 5 For accessing login page on browser use this URI http://localhost:8080/signup-login-0.0.1-SNAPSHOT/
		
	Step 6 if we pull branch from the github the URI will be http://localhost:8080/internship/

				
		
	

Build With 

	Maven Dependency Management.

Authors
	
	Mahendra Gautam Varkhade



		

	
		

	