# GitHub Integration Service

A Spring Boot backend service that fetches repositories from the GitHub API and processes repository analytics using Apache Kafka.

---

## Features

- Fetch GitHub repositories
- Filter by language
- Sort by stars or name
- Pagination support
- Kafka event publishing
- Kafka consumer analytics processing
- Event-driven architecture

---

## Tech Stack

- Java 21
- Spring Boot
- Apache Kafka
- REST APIs
- Maven

---

## API Endpoints

Fetch repositories

GET /repos

Example queries:

Filter by language

GET /repos?language=Java

Sort by stars

GET /repos?sort=stars

Pagination

GET /repos?page=0&size=5

Combined example

GET /repos?language=Java&sort=stars&page=0&size=5

---

## Architecture

Client
│
▼
Spring Boot REST API
│
▼
GitHub REST API
│
▼
Kafka Producer
│
▼
Kafka Topic (repo-events)
│
▼
Kafka Consumer
│
▼
Analytics Engine

---

## AWS Deployment Architecture

Client → API Gateway → Spring Boot Service (ECS)

Spring Boot → Amazon MSK (Kafka)

Kafka Consumer → DynamoDB / Redis

---

## Run the Project

Start Kafka locally

Run the Spring Boot application

Call the API
