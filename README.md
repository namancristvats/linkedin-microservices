# LinkedIn Clone Microservices Application

## Overview

A production-style LinkedIn Clone built using Spring Boot Microservices architecture and deployed on Google Kubernetes Engine (GKE).

The application allows users to create profiles, connect with other professionals, create posts with images, receive notifications, and interact through a scalable cloud-native architecture.

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Cloud
* Spring Security
* Spring Data JPA
* Spring Data Neo4j
* Spring Kafka
* OpenFeign

### Databases

* PostgreSQL
* Neo4j

### Cloud & DevOps

* Docker
* Kubernetes (GKE)
* Google Cloud Platform
* Jib
* Cloudinary

### Messaging

* Apache Kafka

## Architecture

### Services

#### API Gateway

Responsibilities:

* Request Routing
* JWT Authentication
* Central Entry Point

#### User Service

Responsibilities:

* User Registration
* User Login
* Profile Management

Database:

* PostgreSQL

#### Posts Service

Responsibilities:

* Create Post
* Fetch Feed
* Publish Post Events

Database:

* PostgreSQL

#### Connections Service

Responsibilities:

* Send Connection Requests
* Accept Connection Requests
* Manage First Degree Connections

Database:

* Neo4j Graph Database

#### Notification Service

Responsibilities:

* Consume Kafka Events
* Generate Notifications

Database:

* PostgreSQL

#### Uploader Service

Responsibilities:

* Upload Images
* Cloudinary Integration

## Event Driven Flow

1. User creates a post.
2. Posts Service publishes Kafka event.
3. Notification Service consumes event.
4. First-degree connections are identified.
5. Notifications are generated and stored.

## Kubernetes Deployment

All services are containerized using Docker and deployed on GKE.

Components:

* Deployments
* Services
* StatefulSets
* Persistent Volumes
* Ingress

## Security

* JWT Authentication
* API Gateway Filters
* Route-Level Authorization

## Key Features

* User Authentication
* Professional Network Connections
* Post Creation with Images
* Like Post Service
* Kafka-based Notifications
* Cloudinary Image Storage
* Graph-based Connections using Neo4j
* Kubernetes Deployment on GKE

## Future Enhancements

* Comments Service
* Search Service
* Redis Caching
* CI/CD Pipeline using GitHub Actions
* Monitoring using Prometheus & Grafana
* Distributed Tracing using Zipkin
