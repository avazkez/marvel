-- Marvel Database Initialization Script
-- This script will run when the MySQL container starts for the first time

-- Create the database if it doesn't exist (should already be created by docker-compose)
CREATE DATABASE IF NOT EXISTS marvel_db;

-- Switch to the marvel database
USE marvel_db;

-- Grant all privileges to the marvel_user
GRANT ALL PRIVILEGES ON marvel_db.* TO 'marvel_user'@'%';
FLUSH PRIVILEGES;

-- You can add your table creation scripts here
-- Example:
-- CREATE TABLE heroes (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     alias VARCHAR(255),
--     powers TEXT,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- );