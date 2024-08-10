Task Management Application with Pomodoro Timer
Overview
This is a Spring Boot-based application designed to help users manage tasks and time using a Pomodoro timer. The application allows users to create, update, delete, and view tasks, as well as manage user roles and authentication using JWT (JSON Web Token).

Note: I'm still learning Spring Boot, Java, and web development in general, so this project is a work in progress. I'm happy if anyone wants to upgrade the app, contribute a frontend, or provide tips to help me learn more!

Features
1. Task Management
Add Task: Users can add new tasks with a title, description, and status.
Update Task: Users can update existing tasks.
Delete Task: Users can delete tasks.
View Tasks by Person: Users can view tasks assigned to them.
View Tasks by Status: Users can filter tasks based on their status (e.g., TODO, IN_PROGRESS, DONE).
2. Pomodoro Timer
Start Pomodoro Session: A user can start a Pomodoro session which will run for a specified duration (typically 25 minutes).
Break Sessions: After completing a Pomodoro session, users can take short or long breaks depending on the number of Pomodoro sessions completed.
3. User Management
Registration: Users can register for an account.
Authentication: Users can log in to the system using JWT for secure API access.
Role Management: Admins can assign roles to users, such as USER or ADMIN.
4. Security
JWT Authentication: The application uses JWT for securing API endpoints.
Role-Based Access Control: Access to certain endpoints is restricted based on user roles.
Installation
Prerequisites
Java 17 or later
Maven
MySQL or any other relational database
Steps
Clone the Repository:

bash

git clone https://github.com/your-repo/task-manager-app.git
cd task-manager-app
Configure the Database:

Set up a MySQL database.
Update application.properties with your database credentials:
properties

spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build the Application:

bash

mvn clean install
Run the Application:

bash

mvn spring-boot:run
Usage
API Endpoints
1. User Registration and Authentication
Register: POST /api/register
Login: POST /api/login (returns JWT token)
2. Task Management
Add Task: POST /api/tasks
Update Task: PUT /api/tasks/{id}
Delete Task: DELETE /api/tasks/{id}
View Tasks by Person: GET /api/tasks/person/{personId}
View Tasks by Status: GET /api/tasks/status/{status}
3. Pomodoro Timer
Start Pomodoro: POST /api/pomodoro/start
Check Status: GET /api/pomodoro/status
Running Tests
To run the tests for the application:

bash

mvn test
Contributing
As I'm still learning, I would be thrilled if anyone wants to contribute to this project. Whether you want to upgrade the app, create a frontend, or provide tips and resources to help me learn more, your input is welcome!

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

Learning Resources
Here are some resources I've been using to learn:

Spring Boot Documentation: Spring Boot
Java Documentation: Java 17
Maven Documentation: Maven
MySQL Documentation: MySQL
Feel free to suggest other resources that might help me and others who are also learning!



Contact
For any questions, suggestions, or tips, please contact [mos.ougf@gmail.com].
