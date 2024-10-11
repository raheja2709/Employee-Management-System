# Spring Boot Kafka CRUD

## Overview

This project is a Spring Boot application that integrates Apache Kafka to perform CRUD (Create, Read, Update, Delete) operations on employee records. The application utilizes a MySQL database for persistent storage and employs JUnit and Mockito for unit testing.

## Technologies Used

- **Java**: Programming language used to develop the application.
- **Spring Boot**: Framework for building the application quickly with minimal configurations.
- **MySQL**: Relational database management system used for data storage.
- **Apache Kafka**: Distributed event streaming platform for handling real-time data feeds.
- **JUnit**: Framework for unit testing Java applications.
- **Mockito**: Library for creating mock objects in tests.

## API Endpoints

The following API endpoints are available for managing employee records:

1. **Create Employee**
   - **URL**: `POST http://localhost:8080/employees`
   - **Request Body**:
     ```json
     {
       "name": "John Doe",
       "username": "johndoe",
       "department": "IT",
       "salary": 1200.0
     }
     ```

2. **Get All Employees**
   - **URL**: `GET http://localhost:8080/employees`

3. **Get Employee by ID**
   - **URL**: `GET http://localhost:8080/employees/{id}`
   - **Example**: `GET http://localhost:8080/employees/1`

4. **Update Employee**
   - **URL**: `PUT http://localhost:8080/employees/{id}`
   - **Request Body**:
     ```json
     {
       "name": "Jane Doe",
       "username": "janedoe",
       "department": "HR",
       "salary": 1500.0
     }
     ```
   - **Example**: `PUT http://localhost:8080/employees/1`

5. **Delete Employee**
   - **URL**: `DELETE http://localhost:8080/employees/{id}`
   - **Example**: `DELETE http://localhost:8080/employees/1`

## Postman API Collection

You can import the following Postman collection to easily test the API endpoints:

### Collection Information
- **Name**: Spring Boot Kafka CRUD
- **Description**: Collection for testing CRUD operations with Kafka integration

### API Requests
Here are the requests included in the Postman collection:

1. **Create Employee**
   - **Method**: POST
   - **URL**: `http://localhost:8080/employees`
   - **Headers**: `Content-Type: application/json`
   - **Body**:
     ```json
     {
       "name": "John Doe",
       "username": "johndoe",
       "department": "IT",
       "salary": 1200.0
     }
     ```

2. **Get All Employees**
   - **Method**: GET
   - **URL**: `http://localhost:8080/employees`

3. **Get Employee by ID**
   - **Method**: GET
   - **URL**: `http://localhost:8080/employees/{id}`
   - **Example**: `http://localhost:8080/employees/1`

4. **Update Employee**
   - **Method**: PUT
   - **URL**: `http://localhost:8080/employees/{id}`
   - **Headers**: `Content-Type: application/json`
   - **Body**:
     ```json
     {
       "name": "Jane Doe",
       "username": "janedoe",
       "department": "HR",
       "salary": 1500.0
     }
     ```

5. **Delete Employee**
   - **Method**: DELETE
   - **URL**: `http://localhost:8080/employees/{id}`
   - **Example**: `http://localhost:8080/employees/1`

### Importing the Collection
To import the collection into Postman:

1. Open Postman.
2. Click on the "Import" button in the top left.
3. Choose "Raw text" and paste the JSON content of the collection below.
4. Click "Continue" and then "Import".

### Postman Collection JSON
```json
{
  "info": {
    "_postman_id": "1d7f8f1e-8d8e-4b6d-9e9f-348f5ccee435",
    "name": "Spring Boot Kafka CRUD",
    "description": "Collection for testing CRUD operations with Kafka integration",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Employee",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"name\": \"John Doe\",\n  \"username\": \"johndoe\",\n  \"department\": \"IT\",\n  \"salary\": 1200.0\n}"
        },
        "url": {
          "raw": "http://localhost:8080/employees",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "employees"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get All Employees",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/employees",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "employees"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get Employee by ID",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/employees/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "employees",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Update Employee",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "type": "text"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"name\": \"Jane Doe\",\n  \"username\": \"janedoe\",\n  \"department\": \"HR\",\n  \"salary\": 1500.0\n}"
        },
        "url": {
          "raw": "http://localhost:8080/employees/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "employees",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Delete Employee",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/employees/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "employees",
            "1"
          ]
        }
      },
      "response": []
    }
  ]
}
