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
| POST   | /api/parking/park     | Park a new vehicle |
| DELETE | /api/parking/unpark/{vehicleNumber} | Unpark vehicle |
| GET    | /api/parking/vehicles | Get all parked vehicles |
| GET    | /api/parking/status   | Get parking lot status |

##  Deliverables

- REST APIs
- Postman collection (in `/postman`)
- DB Schema (in `/db`)
- Transaction demo in `ParkingService.java`

## Running the App

```bash
./mvnw spring-boot:run
