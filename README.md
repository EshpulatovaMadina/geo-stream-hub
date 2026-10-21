# 🌍 GeoStream Hub

High-performance reactive real-time courier tracking system built with modern Java and Spring Boot stacks. Designed for low-latency event processing, non-blocking database operations, and live location streaming via Server-Sent Events (SSE) and Apache Kafka.

---

## 🚀 Tech Stack & Core Technologies

* **Language & Core:** Java 21, Spring Boot 3.3.4
* **Reactive Stack:** Spring WebFlux, Project Reactor (`Flux`, `Mono`, `Sinks`)
* **Database & Persistence:** PostgreSQL, Spring Data R2DBC (Non-blocking reactive driver)
* **Messaging & Event Streaming:** Apache Kafka
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Maven

---

## ⚙️ Architecture Overview

1. **Location Ingestion:** Couriers send their GPS coordinates via a non-blocking `POST /api/locations` REST endpoint.
2. **Reactive Persistence:** Location points are asynchronously stored in PostgreSQL using R2DBC.
3. **Event Publishing:** Successfully saved locations are published to an Apache Kafka topic (`courier-locations`).
4. **Real-time Multicasting:** Kafka Consumer reads the events and pushes them into a reactive `Sinks.Many` broadcast stream.
5. **Live Streaming:** Clients (maps/dashboards) subscribe via Server-Sent Events (`GET /api/locations/stream` or `GET /api/locations/stream/{courierId}`) to receive live coordinate updates with zero-latency.

---

## 📁 Project Structure

```text
geo-stream-hub/
│
├── src/
│   ├── main/
│   │   ├── java/uz/learn/geostreamhub/
│   │   │   ├── controller/     # Reactive REST & SSE Controllers
│   │   │   ├── service/        # Business Logic & Stream Processing
│   │   │   ├── repository/     # R2DBC Reactive Repositories
│   │   │   ├── model/          # Data Records (LocationPoint)
│   │   │   └── kafka/          # Kafka Producers & Consumers
│   │   │
│   │   └── resources/
│   │       └── application.yml # App configuration
│   └── test/
│
├── docker-compose.yml          # Infrastructure setup (PostgreSQL, Kafka, Zookeeper)
├── pom.xml                     # Maven dependencies
└── README.md
