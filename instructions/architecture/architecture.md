
# Architecture Instructions

## Overview

- Describe the overall system architecture (layers, modules, dependencies).
- Include diagrams for major components and their interactions.

## Guidelines

- Follow layered architecture: Controller, Service, Repository, Domain.
- Use dependency injection for service and repository layers.
- Keep controllers thin; business logic belongs in services.
- Repositories should only handle data access.
- DTOs and Criteria objects for data transfer and filtering.

## Best Practices

- Document architectural decisions and rationale.
- Use design patterns where appropriate (e.g., Singleton, Factory).
- Keep code loosely coupled and highly cohesive.

## Resources

- [Spring Boot Architecture](https://docs.spring.io/spring-boot/docs/current/reference/html/architecture.html)
