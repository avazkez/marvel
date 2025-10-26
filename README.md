# Marvel API Application

A Spring Boot application for accessing and managing character and comic data through the Marvel Developer API.

## 📝 Overview

This project provides a RESTful API to interact with Marvel character and comic data, leveraging the official Marvel Developer API. It supports secure access, filtering, pagination, and follows best practices for code quality and maintainability.

**Main Features:**

- Secure API endpoints for Marvel characters and comics
- Pagination and filtering support
- OpenAPI/Swagger documentation
- Dockerized MySQL setup for local development
- Static analysis and code style enforcement

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

3. **Set your environment variables:**

   If you use a `.env` file, you must export the variables before running the application:

   ```bash
   export MARVEL_API_PUBLIC_KEY=your_actual_public_key
   export MARVEL_API_PRIVATE_KEY=your_actual_private_key
   export DB_USERNAME=marvel_user
   export DB_PASSWORD=marvel_password
   ```

   If you use a `.envrc` file, you can manage environment variables automatically with [direnv](https://direnv.net/):

   ```bash
   # .envrc example
   export MARVEL_API_PUBLIC_KEY=your_actual_public_key
   export MARVEL_API_PRIVATE_KEY=your_actual_private_key
   export DB_USERNAME=marvel_user
   export DB_PASSWORD=marvel_password
   ```

   Then run:

   ```bash
   direnv allow
   ```

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0 (local or Docker)
- Marvel API credentials (see setup below)

1. **Create a Marvel Account:**
   - Visit: <https://www.marvel.com/signin?referer=https%3A%2F%2Fdeveloper.marvel.com%2Faccount>
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

2. **Set up environment variables:** (See Security & Configuration section above)

3. **Start the database:**

   ```bash
   docker-compose up -d mysql
   ```

4. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

- Swagger UI: <http://localhost:8080/swagger-ui/index.html>
- API Docs: <http://localhost:8080/v3/api-docs>

---

## 🧪 Testing & Code Quality

### Run Tests

```bash
./mvnw test
```

### Static Analysis Tools

**SpotBugs - Bug Detection:**

```bash
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
  
### Characters API

- `GET /api/characters`  
   Retrieve a paginated list of Marvel characters. Supports filtering by name, comics, and series.  
   **Query params:** `name`, `comics[]`, `series[]`, `offset`, `limit`  
   **Auth:** `character:read-all`

- `GET /api/characters/{characterId}`  
   Retrieve detailed information for a specific Marvel character by ID.  
   **Auth:** `character:read-detail`

### Comics API

- `GET /api/comics`  
   Retrieve a paginated list of Marvel comics. Supports filtering by character ID.  
   **Query params:** `characterId`, `offset`, `limit`  
   **Auth:** `comic:read-all`

- `GET /api/comics/{comicId}`  
   Retrieve detailed information for a specific Marvel comic by ID.  
   **Auth:** `comic:read-by-id`

### Authentication API

- `POST /auth/login`  
   Authenticate user and return JWT token.  
   **Body:** `username`, `password`  
   **Auth:** Public

- `POST /auth/logout`  
   Logout current user and terminate session.  
   **Auth:** Public

### User Interaction Logs API

- `GET /user-interaction-logs`  
   Retrieve a paginated list of all user interaction logs.  
   **Query params:** `offset`, `limit`  
   **Auth:** `user-interaction:read-all`

- `GET /user-interaction-logs/{username}`  
   Retrieve a paginated list of user interaction logs for a specific username.  
   **Query params:** `offset`, `limit`  
   **Auth:** `user-interaction:read-by-username` or `user-interaction:read-my-interactions` (for own logs)

### Example Usage

**Get all characters (paginated):**

```bash
curl -X GET "http://localhost:8080/api/characters?page=0&size=10"
```

**Get a comic by ID:**

```bash
curl -X GET "http://localhost:8080/api/comics/123"
```

## 🧪 API Testing with Postman

This repository includes a ready-to-use Postman collection for testing the Marvel API endpoints.

- Import the file `postman/api_test.postman_collection.json` into Postman.
- The collection covers authentication, character, comic, and user interaction endpoints.
- You can use the provided requests and environment to quickly validate API functionality.

See the [Postman documentation](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/) for instructions on importing collections.

---

## 🏗️ Project Structure

```text
src/
├── main/
│   ├── java/com/avazquez/api/marvel/
│   │   ├── MarvelApplication.java
│   │   ├── config/
│   │   │   └── OpenApiConfig.java
│   │   ├── controller/
│   │   │   ├── CharacterController.java
│   │   │   └── ComicController.java
│   │   ├── criteria/
│   │   │   ├── CharacterSearchCriteria.java
│   │   │   └── ComicSearchCriteria.java
│   │   ├── dto/
│   │   │   ├── GetUserInteractionLogDto.java
│   │   │   ├── MyPageable.java
│   │   │   ├── exception/
│   │   │   │   └── ApiErrorDto.java
│   │   │   └── security/
│   │   │       ├── LoginRequest.java
│   │   │       └── LoginResponse.java
│   │   ├── exception/
│   │   │   ├── ApiErrorException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── mapper/
│   │   │   └── UserInteractionLogMapper.java
│   │   ├── persistence/
│   │   │   ├── entity/
│   │   │   │   ├── GrantedPermission.java
│   │   │   │   ├── Permission.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── User.java
│   │   │   │   └── UserInteractionLog.java
│   │   │   ├── integration/
│   │   │   │   └── marvel/
│   │   │   │       ├── MarvelApiConfig.java
│   │   │   │       ├── dto/
│   │   │   │       │   ├── CharacterDto.java
│   │   │   │       │   ├── ComicDto.java
│   │   │   │       │   └── ThumbnailDto.java
│   │   │   │       ├── mapper/
│   │   │   │       │   ├── CharacterMapper.java
│   │   │   │       │   ├── ComicMapper.java
│   │   │   │       │   ├── ComicQueryParameterMapper.java
│   │   │   │       │   ├── MarvelQueryParameterMapper.java
│   │   │   │       │   └── ThumbnailMapper.java
│   │   │   │       └── repository/
│   │   │   │           ├── CharacterRepository.java
│   │   │   │           └── ComicRepository.java
│   │   │   ├── repository/
│   │   │   │   ├── UserInteractionLogRepository.java
│   │   │   │   └── UserRepository.java
│   │   ├── security/
│   │   │   ├── SecurityBeansInjector.java
│   │   │   ├── WebSecurity.java
│   │   │   └── validator/
│   │   │       └── UserInteractionLogValidator.java
│   │   ├── service/
│   │   │   ├── AuthenticationService.java
│   │   │   ├── CharacterService.java
│   │   │   ├── ComicService.java
│   │   │   ├── HttpClientService.java
│   │   │   ├── JwtService.java
│   │   │   ├── UserInteractionLogService.java
│   │   │   └── impl/
│   │   │       ├── CharacterServiceImpl.java
│   │   │       ├── ComicServiceImpl.java
│   │   │       ├── RestTemplateService.java
│   │   │       └── UserInteractionLogServiceImpl.java
│   │   ├── util/
│   │   │   ├── GenericBeansInjector.java
│   │   │   ├── MD5Encoder.java
│   │   │   └── MarvelApiHeaderUtil.java
│   │   ├── web/
│   │   │   ├── controller/
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   └── UserInteractionLogController.java
│   │   │   ├── filter/
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   └── interceptor/
│   │   │       ├── InterceptorsConfig.java
│   │   │       └── UserInteractionInterceptor.java
│   └── resources/
│       ├── META-INF/
│       │   └── spring-configuration-metadata.json
│       ├── application-dev.properties
│       ├── application-prod.properties
│       ├── application.properties
│       └── data-mysql.sql
├── test/
│   └── java/com/avazquez/api/marvel/
│       └── MarvelApplicationTests.java
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

**API Documentation**: Available at `/swagger-ui/index.html` when running.

**Database Setup**: See [DATABASE.md](DATABASE.md)

**Javadoc**: Auto-generated Java API documentation can be found in `target/reports/apidocs/index.html` after running:

```bash
./mvnw javadoc:javadoc
```

---

## 📬 Contact & Support

For support, questions, or feedback, open an issue in this repository or contact [@avazkez](https://github.com/avazkez).
