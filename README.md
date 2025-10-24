# Marvel API Application

A Spring Boot application for accessing and managing Marvel character and comic data through the Marvel Developer API.

## 🛡️ Security & Configuration

This application follows security best practices by using environment variables for sensitive configuration data.

### Environment Variables Setup

1. **Copy the example environment file:**
   ```bash
   cp .env.example .env
   ```

2. **Get your Marvel API credentials:**
   - Visit [Marvel Developer Portal](https://developer.marvel.com/account)
   - Create an account or sign in
   - Generate your public and private API keys

3. **Update the `.env` file with your actual values:**
   ```bash
   MARVEL_API_PUBLIC_KEY=your_actual_public_key
   MARVEL_API_PRIVATE_KEY=your_actual_private_key
   DB_USERNAME=marvel_user
   DB_PASSWORD=marvel_password
   ```

### Why Environment Variables?

- ✅ **Security**: Sensitive data is never committed to version control
- ✅ **Flexibility**: Easy to change configuration per environment
- ✅ **Best Practice**: Industry standard for managing secrets
- ✅ **CI/CD Ready**: Compatible with deployment platforms

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0
- Marvel API credentials (see setup below)

### 🔑 Marvel API Credentials Setup

To use this application, you need Marvel API credentials:

1. **Create a Marvel Account:**
   - Visit: https://www.marvel.com/signin?referer=https%3A%2F%2Fdeveloper.marvel.com%2Faccount
   - Sign up for a free Marvel account

2. **Get Developer Access:**
   - Navigate to the [Marvel Developer Portal](https://developer.marvel.com/account)
   - Accept the terms and conditions to get your API keys

3. **Obtain Your Keys:**
   - **Public Key**: Safe to use in client-side code (used in `apikey` parameter)
   - **Private Key**: **KEEP SECRET** - used for server-side hash generation only

**⚠️ Security Warning:** Never expose your private key in public repositories or client-side code!

### Local Development Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/avazkez/marvel.git
   cd marvel
   ```

2. **Set up environment variables:** (See Security & Configuration section above)

3. **Start the database:**
   ```bash
   docker-compose up -d mysql
   ```

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the API documentation:**
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - API Docs: http://localhost:8080/api-docs

## 🧪 Testing & Code Quality

### Run Tests
```bash
./mvnw test
```

### Static Analysis Tools

**SpotBugs - Bug Detection:**
```bash
./mvnw spotbugs:check                # Run analysis
./mvnw spotbugs:gui                  # View GUI (optional)
```

**Checkstyle - Code Style:**
```bash
./mvnw checkstyle:check              # Check Google Java Style
```

**Google Java Format:**
```bash
./mvnw com.spotify.fmt:fmt-maven-plugin:format  # Auto-format code
```

**Run All Quality Checks:**
```bash
./mvnw compile spotbugs:check checkstyle:check
```

## 📚 API Endpoints

### Characters
- `GET /api/characters` - List all characters with pagination and filtering
- `GET /api/characters/{id}` - Get character details by ID

### Comics
- `GET /api/comics` - List all comics with pagination and filtering
- `GET /api/comics/{id}` - Get comic details by ID

## 🏗️ Project Structure

```
src/
├── main/java/com/avazquez/api/marvel/
│   ├── controller/          # REST controllers
│   ├── service/             # Business logic layer
│   ├── persistence/         # Data access layer
│   ├── dto/                 # Data transfer objects
│   └── config/              # Configuration classes
└── main/resources/
    ├── application.properties          # Base configuration
    ├── application-dev.properties      # Development settings
    └── application-prod.properties     # Production settings
```

## 🛠️ Technologies Used

- **Spring Boot 3.5.6** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication & authorization
- **MySQL** - Database
- **SpringDoc OpenAPI** - API documentation
- **Maven** - Dependency management
- **Docker** - Containerization

## 📖 Documentation

- **API Documentation**: Available at `/swagger-ui.html` when running
- **Database Setup**: See [DATABASE.md](DATABASE.md)
- **Deployment Guide**: Coming soon

## 🤝 Contributing

This is a technical assessment project. For questions or suggestions, please create an issue.

## 📄 License

This project is created for technical assessment purposes.