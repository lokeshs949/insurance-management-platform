# #insurance-management-platform.


this is an insurance management platform that allows users to manage insurance
policies, clients, and claims using Spring Boot and Java.

##Getting Started
#Prerequisites
To run this project, you will need to have the following software installed on your machine:

Java 8 or higher
Apache Maven 3.x or higher
Installing
Clone this repository to your local machine.

Open a terminal window and navigate to the project directory.

Run the following command to build the project:

```shell
mvn clean install
Once the build is complete, run the following command to start the application:
```
```shell
mvn spring-boot:run
The application will start and be available at http://localhost:8080.
```

## API Reference
The API provides the following endpoints:

a. Clients:
i. GET /api/clients: Fetch all clients.
ii. GET /api/client/{id}: Fetch a specific client by ID.
iii. POST /api/client: Create a new client.
iv. PUT /api/Uclient/{id}: Update a client's information.
v. DELETE /api/Dclient/{id}: Delete a client.

b. Insurance Policies:
i. GET /api/policies: Fetch all insurance policies.
ii. GET /api/policie/{id}: Fetch a specific insurance policy by ID.
iii. POST /api/policy: Create a new insurance policy.
iv. PUT /api/Upolicy/{id}: Update an insurance policy.
v. DELETE /api/Dpolicy/{id}: Delete an insurance policy.

c. Claims:
i. GET /api/claims: Fetch all claims.
ii. GET /api/claim/{id}: Fetch a specific claim by ID.
iii. POST /api/claim: Create a new claim.
iv. PUT /api/Uclaim/{id}: Update a claim's information.
v. DELETE /api/Dclaim/{id}: Delete a claim.

Built With
Spring Boot - The web framework used
Maven - Dependency management
H2 Database - In-memory database
