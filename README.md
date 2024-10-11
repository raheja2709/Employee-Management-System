# Employee-Management-System
This project is a Spring Boot application that manages Employee entities with CRUD operations using Spring Data JPA and MySQL. It leverages Apache Kafka for event-driven messaging, logging changes to an audit table. Unit tests are included using JUnit and Mockito

# Employee-Management-System
This project is a Spring Boot application that manages Employee entities with CRUD operations using Spring Data JPA and MySQL. It leverages Apache Kafka for event-driven messaging, logging changes to an audit table. Unit tests are included using JUnit and Mockito

# Steps to Run Project 
- Run Zookeper Firest by below commond 
  Kafka Directory > bin\windows\zookeeper-server-start.bat config\zookeeper.properties
- Run Apache Kafka
  Kafka Directory > bin\windows\kafka-server-start.bat config\server.properties
- To check the event driven messages in employee_events topic
  Kafka Directory > bin\windows\kafka-console-consumer.bat --topic employee_events --from-beginning --bootstrap-server localhost:9092
 
# Employee_Management_API.postman_collection

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
