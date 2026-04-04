# Database Design - MailCraft

## Overview
This project uses a relational database with 3 main tables:

1. Users
2. Email Requests
3. Generated Emails

---

## 1. Users Table
Stores registered user details.

Fields:
- id (Primary Key)
- name
- email (Unique)
- password
- created_at

---

## 2. Email Requests Table
Stores job application input data provided by users.

Fields:
- id (Primary Key)
- position
- company
- job_description
- user_id (Foreign Key → users.id)
- created_at

---

## 3. Generated Emails Table
Stores AI-generated emails.

Fields:
- id (Primary Key)
- subject
- body
- email_request_id (Foreign Key → email_requests.id)
- generated_at

---

## Relationships
- One User → Many Email Requests
- One Email Request → Many Generated Emails