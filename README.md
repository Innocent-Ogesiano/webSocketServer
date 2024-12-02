# webSocketServer

This application is a backend server built with Spring Boot, enabling WebSocket communication and integrating PostgreSQL for real-time event storage and retrieval.

## **Features**
- Establishes a WebSocket connection at `/ws/events`.
- Handles five specific events, each with a defined structure.
- Persists events in a PostgreSQL database.
- Validates incoming data and provides appropriate feedback.

## **Requirements**

Ensure the following are installed on your local machine:

1. **Java 17** or higher
2. **PostgreSQL 13** or higher
3. **Maven 3.6** or higher

## **Setup Instructions**

### 1. **Clone the Repository**

```bash
git clone <repository-url>
cd <project-folder>
```

### 2. **Configure PostgreSQL Database**

1. Create a database named `realtime_db` (or any name of your choice).
2. Update `src/main/resources/application.properties` with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/event_db
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

### 3. **Build the Application**

Run the following command to build the project:

```bash
mvn clean install
```

### 4. **Run the Application**

Start the application using:

```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`.

### 5. **Test the WebSocket Endpoint**

1. Connect to the WebSocket endpoint:  
   **URL**: `ws://localhost:8080/ws/events`

2. Use a WebSocket client like:
    - Postman
    - [websocket.org](https://www.websocket.org/echo.html)
    - Custom frontend or browser extensions.

3. Send a sample WebSocket message:
   ```json
   {
       "eventType": "user_connected",
       "payload": {
           "userId": "12345",
           "timestamp": "2024-12-02T12:34:56"
       }
   }
   ```

4. Verify the response. The server will confirm successful processing.

### 6. **Verify Event Persistence**

1. Open a PostgreSQL client (e.g., `psql` or a GUI tool like pgAdmin).
2. Run the following query to view stored events:
   ```sql
   SELECT * FROM event;
   ```