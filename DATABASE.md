# Marvel API Database Setup

This project uses MySQL as the database, configured to run in Docker containers.

## Prerequisites

- Docker
- Docker Compose

## Database Setup

### Using Docker Compose

1. **Start the MySQL database:**
   ```bash
   docker-compose up -d mysql
   ```

2. **Check if the container is running:**
   ```bash
   docker-compose ps
   ```

3. **View database logs:**
   ```bash
   docker-compose logs mysql
   ```

4. **Connect to the database:**
   ```bash
   docker-compose exec mysql mysql -u marvel_user -p marvel_db
   ```
   Password: `marvel_password`

5. **Stop the database:**
   ```bash
   docker-compose down
   ```

6. **Stop and remove volumes (WARNING: This will delete all data):**
   ```bash
   docker-compose down -v
   ```

### Database Configuration

The database is configured with:
- **Database Name:** `marvel_db`
- **Username:** `marvel_user`
- **Password:** `marvel_password`
- **Port:** `3306`
- **Host:** `localhost` (when running the Spring Boot app locally)

### Initialization Scripts

Database initialization scripts are located in `db/init/`. These scripts run automatically when the MySQL container starts for the first time.

### Data Persistence

Database data is persisted in a Docker volume named `mysql_data`. This ensures your data survives container restarts.

## Environment Profiles

The application supports different profiles:
- `dev` - Development environment
- `prod` - Production environment

Make sure to update your `application.properties` or use environment-specific property files as needed.