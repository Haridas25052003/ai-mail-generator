📨 AI Job Application Mail Generator (Backend)
A robust Spring Boot backend that leverages Artificial Intelligence to craft professional, tailored job application emails. By analyzing job descriptions and company details, this system automates the tedious part of the job hunt while maintaining a personalized touch.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
🛠️ Tech Stack
Language: Java (17+)

Framework: Spring Boot (Data JPA, Web)

Database: MySQL

ORM: Hibernate

AI Integration: Groq Cloud API / LLM REST Integration

Build Tool: Maven
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
🚀 Key Features
AI-Powered Generation: Generates context-aware emails based on job designation and company culture.

User Persistence: Simple email-based identification to track history without complex password overhead.

History Retrieval: Users can access all their previously generated emails at any time.

Layered Architecture: Follows industry-standard Controller-Service-Repository patterns.

Prompt Engineering: Structured backend logic to ensure the AI output is professional and formatted correctly.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
🏗️ Application Architecture
The project is built with a focus on scalability and clean code:

Controller Layer: Manages REST endpoints and request validation.

Service Layer: Orchestrates business logic and handles external AI API communication.

Repository Layer: Utilizes Spring Data JPA for seamless MySQL operations.

Entity Layer: Defines the schema for Users, Email Requests, and Generated Content.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
🔗 REST API Endpoints
🔹 User Authentication
POST /auth/register

Purpose: Register or identify a user.

Body: { "name": "string", "email": "string" }

🔹 Email Generation
POST /api/email/generate

Purpose: Triggers AI generation and stores the result.

Body: { "userId": Long, "designation": "string", "company": "string", "jobDescription": "text" }

🔹 History Management
GET /api/email/history/{userId}

Purpose: Retrieves all saved emails for the specific user.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
⚙️ Configuration & Setup
Clone the repository:

Bash
git clone https://github.com/Haridas25052003/your-repo-name.git
Configure application.properties:

Set your spring.datasource.url, username, and password.

Add your AI API Key: ai.api.key=your_groq_key_here.

Run the application:

Bash
mvn spring-boot:run
📈 Future Enhancements
[ ] JWT Authentication: Adding secure login layers.

[ ] PDF Export: Allow users to download the generated email as a PDF.

[ ] Multi-Tone Selection: Options for "Formal," "Creative," or "Short" email styles.

[ ] Attachment Parsing: Extracting data directly from uploaded Resumes.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
👨‍💻 Author
Haridas Shinde

Role: Backend Developer

LinkedIn: Your Profile

Portfolio: haridas-portfolio.vercel.app
