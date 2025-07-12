# URL Shortener - Backend Service

## Overview
A high-performance URL shortening service built with Java Spring Boot, utilizing Redis for caching and MySQL for persistent storage. The system provides fast URL redirection while tracking click analytics.

## Backend Architecture
The backend is designed with a focus on performance, scalability, and reliability, using a multi-layer caching strategy:

1. **Redis Cache**: First-level cache for ultra-fast response times
2. **MySQL Database**: Persistent storage for all URLs and analytics
3. **Spring Boot**: Robust Java framework handling business logic

## Key Features

### URL Shortening Flow
1. User submits a URL via POST request
2. System checks Redis cache first
3. If not in cache, checks MySQL database
4. If not found, generates a unique slug and stores in both cache and database
5. Returns the shortened URL to the user

### Redirection Flow
1. User accesses shortened URL via GET request
2. System checks Redis cache for the original URL
3. If not in cache, fetches from MySQL
4. Increments click counter in database
5. Redirects user to original URL

### Analytics
- Real-time click tracking
- Persistent storage of all access data
- Fast retrieval through indexed database queries

## Technologies

### Backend Stack
- **Java**: Core application language
- **Spring Boot**: Framework for backend services
- **Spring Data JPA**: Database abstraction layer
- **Redis**: In-memory cache store
- **MySQL**: Relational database for persistent storage
- **Lettuce**: Redis Java client

### Frontend Stack
- HTML
- CSS
- JavaScript
