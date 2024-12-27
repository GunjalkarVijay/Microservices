# Microservices Project

This project is a demonstration of my learning journey into microservices architecture. It involves various concepts and tools commonly used in building and managing microservices.

## Key Features and Concepts Explored

1. Service Discovery with Eureka:
   - Implemented Eureka Server for service discovery, enabling seamless interaction between microservices.
   - All services are registered with the Eureka Server, allowing dynamic scaling and fault tolerance.

2. Fault Tolerance with Hystrix:
   - Integrated Hystrix to handle failures gracefully and maintain system reliability.
   - Used fallback methods to ensure service continuity during failures.

3. Service-to-Service Communication:
   - Initially used RestTemplate for communication between microservices.
   - Later transitioned to Feign Client for declarative REST client support, while keeping both approaches in the project for comparison.

4. Database Integration:
   - Used Spring Data JPA to interact with a MySQL database.
   - Ensured efficient data handling and persistence for each microservice.

5. Spring Boot Framework:
   - Leveraged Spring Boot to build and configure microservices quickly and efficiently.
   - Simplified application development with features like dependency injection, auto-configuration, and embedded servers.

## Project Structure

1. Eureka Server:
   - Manages service registration and discovery.

2. Microservices:
   - Example service showcasing changes for interaction.
   - Communication via both RestTemplate and Feign Client.

3. Database Integration:
   - Configured with Spring Data JPA and MySQL.

## Tools and Technologies Used

- Spring Boot
- Eureka (Service Discovery)
- Hystrix (Fault Tolerance)
- Feign Client and RestTemplate
- Spring Data JPA
- MySQL
- Maven

---

Feel free to explore and contribute! 😊
