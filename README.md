Spring Boot project with Microservices 

This project has below components

1)Authorizationservice-apigateway : microservice created using spring initializr (acts as a apiGateway)
Added below dependency to pom.xml file 

<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
		
Added the below properties to the application.properties file	
	
application.properties	
	
server.port=8082
spring.application.name=springcloudapigateway
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
		

2)profile-service :microservice created using spring initializr

This microservice is registered with the Eureka Server

Added the below dependency in pom.xml
<dependency>
    		<groupId>org.springframework.cloud</groupId>
    		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			<version>3.1.2</version>
</dependency>

Added the below properties to the application.properties

server.port=8081
spring.application.name=profile-service
eureka.client.service-url.default-zone =http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
spring.devtools.restart.enabled=true

3)discovery-server:it helps micro services(Authorizationservice-apigateway  and profile-service) find each other.It is a registry for registering with eureka server ,Authorizationservice as a apigateway

Added the below dependency in pom.xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

Added the below properties to application.properties

server.port=8761
spring.application.name=discoveryservice
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.service-url.default-zone =http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true


To configure custom routes and make navigation to the micro-services through Authorizationservice-apigateway which is running on 8082,
added the following in application.properties of Authorizationservice-apigateway microservice

spring.cloud.gateway.routes[0].id=profile-service
spring.cloud.gateway.routes[0].uri=lb://profile-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/assignement/create

spring.cloud.gateway.routes[1].id=profile-service
spring.cloud.gateway.routes[1].uri=lb://profile-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/assignement/getAll

spring.cloud.gateway.routes[2].id=profile-service
spring.cloud.gateway.routes[2].uri=lb://profile-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/assignement/update

spring.cloud.gateway.routes[3].id=profile-service
spring.cloud.gateway.routes[3].uri=lb://profile-service
spring.cloud.gateway.routes[3].predicates[0]=Path=/assignement/delete

For Aynchronous communication between microservices, WebClient is used
In-memory database used : H2 is used
Persistence framework used : Spring Data JPA 


Unit tests written for Controller and Service layer of Authorizationservice-apigateway using Junit Jupiter and Mockito


Note:As I renamed the projects in eclipse,when you import these projects in eclipse,it shows old names in brackets infront of project names.
Please ignore those inside brackets.

Getting started:
Install Eclipse IDE
Install java 1.8
Import the projects into Eclipse.

Steps:
1)start the discovery-server
2)start the Authorizationservice-apigateway 
3)start profile-service
Once all the serevrs are up,start hitting the Apis

/register
registerUserProfile:
creation of the username and password in the authorization database
http://localhost:8082/assignement/register
POST
Sample RequestBody json structure
{  
     "username": "user1",
     "password": "pwd1"
}



/login
loginUserProfile:
User is authentocated if the passed username and password are in the database
http://localhost:8082/assignement/login
POST
Sample RequestBody json structure

{  
     "username": "user1",
     "password": "pwd1"
}


/create
createUserProfile:
the user will be creating their own profile info if they are authenticated.
http://localhost:8082/assignement/create
POST:
Sample RequestBody json structure

{  
     "username": "user1",
     "password": "pwd1",
     "phoneNumber":"6478963528",
     "address1":"Toronto"
}


/update
updateUserProfile:
If the user is authenticated they can update their own profile only.
http://localhost:8082/assignement/update

PUT
Sample RequestBody json structure

{    "id" : 1,
     "username": "user1",
     "password": "pwd1",
     "phoneNumber":"6478963529",
     "address1": "Missisauga"
}

/delete
deleteUserProfile:
If the user is authenticated they can delete their own profile only
http://localhost:8082/assignement/delete

DELETE
Sample RequestBody json structure
{    "id" : 1,
     "username": "user1",
     "password": "pwd1",
     "phoneNumber":"6478963529",
     "address1": "Missisauga"
}

