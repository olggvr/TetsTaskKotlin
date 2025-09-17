# User Registration Service

## Using stack
- Kotlin + Spring Boot
- PostgreSQL  
- RabbitMQ  
- Docker Compose  

## Build & run
```
docker compose up -d
./gradlew bootRun
```

## Test request
```
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com"}'
```
