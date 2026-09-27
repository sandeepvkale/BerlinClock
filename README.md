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