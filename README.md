# TASK - Implementing BerlinClock project - BNPP
A TDD-oriented Java/Spring Boot backend and React frontend implementing the Berlin Clock kata.

## Architecture

React UI (5173)
      |
      | HTTP GET
      v
Spring REST Controller (8080)
      |
      v
BerlinClockService
      |
      v
BerlinClock domain record

The conversion rules are kept in one service so the business logic is independent of HTTP and React.

## Run backend
```bash
cd backend
mvn test
mvn spring-boot:run
```

The backend starts on `http://localhost:8080`.

Example:

```text
GET http://localhost:8080/api/clock/to-berlin?time=16:50:06
```
returns:
```text
YRRROROOOYYRYYRYYRYOOOOO
```

## Run frontend

```bash
cd frontend
npm install
npm run dev
```

Open the Vite URL shown in the terminal, normally `http://localhost:5173`.
