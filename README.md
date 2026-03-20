# GitHub Integration Service

A production-ready backend service that integrates with the GitHub REST API, 
built with Spring Boot and Apache Kafka for real-time async processing.

## What it does
- Fetches GitHub repositories with filtering, sorting, and pagination
- Publishes repo fetch events to Kafka topics (event-driven architecture)
- Consumes events asynchronously via Kafka consumer groups
- Caching layer for ~40% performance improvement over synchronous calls
- Cloud-ready architecture: AWS API Gateway + ECS + Amazon MSK

## Tech Stack
| Layer | Technology |
|-------|-----------|
| Backend | Java, Spring Boot |
| Messaging | Apache Kafka (Producer + Consumer) |
| API | GitHub REST API v3 |
| Caching | Spring Cache |
| Build | Maven |
| Cloud | AWS API Gateway, ECS, Amazon MSK |

## Project Structure
```
src/main/java/com/example/githubintegration/
├── client/          # GitHub API HTTP client
├── config/          # Kafka and app configuration  
├── consumer/        # Kafka event consumers
├── controller/      # REST API endpoints
├── event/           # Event model definitions
├── kafka/           # Kafka producers
├── model/           # Domain models
└── service/         # Business logic layer
```

## Key Design Decisions
- **Async processing**: Kafka decouples API ingestion from analytics processing
- **Event-driven**: RepoFetchEvent published on every fetch for downstream consumers
- **Caching**: Reduces redundant GitHub API calls, respects rate limits
- **Cloud-ready**: Designed for containerised deployment on AWS ECS with MSK

## Running Locally
```bash
# Prerequisites: Java 17+, Maven, Kafka running on localhost:9092
git clone https://github.com/arvind7kuntal/github-integration-service.git
cd github-integration-service
mvn spring-boot:run
```

## Author
**Arvind Kumar** — Java Backend Engineer  
[LinkedIn](https://linkedin.com/in/arvind7kuntal) • arvind7kuntal@gmail.com
