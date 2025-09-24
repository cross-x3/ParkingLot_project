# ParkingLot Management System 🚗

A simple Spring Boot project with REST APIs for managing a parking lot.

## Tech Stack
- Spring Boot
- Spring Data JPA
- MySQL / H2
- Postman

##  API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/parking/entry     | Park a new vehicle |
| POST | /api/parking/exit/{ticketId}/pay | Unpark vehicle |
| GET    | /api/parking/{ticketId} | Get parked vehicle details|


##  Deliverables

- REST APIs
- Postman collection (in `/postman`)
- DB Schema (in `/db`)
- Transaction demo in `ParkingService.java`

## Running the App

```bash
./mvnw spring-boot:run
