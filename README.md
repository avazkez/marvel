# Marvel API

A Spring Boot REST API for managing Marvel characters and related data.

## 🚀 Features

- RESTful API for Marvel characters
- MySQL database integration
- Docker containerization
- CI/CD pipeline with GitHub Actions
- Comprehensive testing

## 🛠️ Tech Stack

- **Backend:** Spring Boot 3.x, Java 17
- **Database:** MySQL 8.0
- **Build Tool:** Maven
- **Containerization:** Docker & Docker Compose
- **CI/CD:** GitHub Actions

## 🏃‍♂️ Quick Start

### Prerequisites

- Java 17+
- Docker & Docker Compose
- Maven (or use the included Maven wrapper)

### Running with Docker

1. **Clone the repository:**
   ```bash
   git clone https://github.com/alex93vaz/marvel.git
   cd marvel
   ```

2. **Start the database:**
   ```bash
   docker-compose up -d mysql
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

The API will be available at `http://localhost:8080`

### Running Tests

```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report
```

## 🗄️ Database

The application uses MySQL with the following configuration:
- **Database:** `marvel_db`
- **Username:** `marvel_user`
- **Password:** `marvel_password`
- **Port:** `3306`

For detailed database setup instructions, see [DATABASE.md](DATABASE.md).

## 🔧 Configuration

The application supports multiple profiles:
- `dev` - Development environment
- `prod` - Production environment

Configure via `application-{profile}.properties` files.

## 🚀 API Endpoints

*Coming soon - API documentation will be added as endpoints are implemented*

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Alex Vazquez**
- Email: alex93vaz@hotmail.com
- GitHub: [@alex93vaz](https://github.com/alex93vaz)