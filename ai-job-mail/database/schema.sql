-- Database: mailcraft_db

-- ================================
-- 1. USERS TABLE
-- ================================
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================
-- 2. EMAIL REQUESTS TABLE
-- ================================
CREATE TABLE email_requests (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                position VARCHAR(255) NOT NULL,
                                company VARCHAR(255) NOT NULL,
                                job_description TEXT NOT NULL,
                                user_id BIGINT NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                CONSTRAINT fk_email_request_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES users(id)
                                        ON DELETE CASCADE
);

-- ================================
-- 3. GENERATED EMAILS TABLE
-- ================================
CREATE TABLE generated_emails (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  subject VARCHAR(255),
                                  body TEXT,
                                  email_request_id BIGINT NOT NULL,
                                  generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT fk_generated_email_request
                                      FOREIGN KEY (email_request_id)
                                          REFERENCES email_requests(id)
                                          ON DELETE CASCADE
);