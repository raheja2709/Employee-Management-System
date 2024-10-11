package com.task.emp.mng.sys.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.task.emp.mng.sys.entity.AuditLog;
import com.task.emp.mng.sys.repository.AuditLogRepository;

/**
 * This service listens to Kafka messages related to employee events and logs
 * them as audit entries. The messages are expected to contain an event type and
 * an entity ID separated by a delimiter ": ".
 * 
 * The service listens to the "employee_events" Kafka topic and processes each
 * message by splitting it into the event type and entity ID. It then creates an
 * {@link AuditLog} entry with this information and stores it in the database
 * using {@link AuditLogRepository}.
 * 
 * <p>
 * Example message format: {@code EmployeeCreated: 123}
 * </p>
 * 
 * <p>
 * If the message does not contain the expected delimiter or format, an error is logged.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@Service
public class KafkaConsumerServiceImpl {

	@Autowired
	private AuditLogRepository auditLogRepository;

	/**
	 * Consumes Kafka messages from the "employee_events" topic.
	 * 
	 * <p>
	 * The messages are expected to be in the format {@code eventType: entityId}.
	 * The method splits the message based on the delimiter ": " and creates an
	 * audit log entry with the event type and entity ID. The timestamp of the event
	 * is also recorded.
	 * </p>
	 * 
	 * @param message the Kafka message in the format "eventType: entityId"
	 */
	@KafkaListener(topics = "employee_events", groupId = "my-group")
	public void consume(String message) {
		// Ensure that the message contains the expected delimiter ": "
		if (message.contains(": ")) {
			String[] parts = message.split(": ");

			// Ensure the message was properly split into two parts
			if (parts.length == 2) {
				String eventType = parts[0];
				String entityId = parts[1];

				AuditLog log = new AuditLog();
				log.setEventType(eventType);
				log.setEntityName("Employee");
				log.setEntityId(entityId);
				log.setTimestamp(LocalDateTime.now().toString());

				// Save the audit log entry
				auditLogRepository.save(log);
			} else {
				// Log an error or warning for incorrect message format
				System.err.println("Invalid message format: " + message);
			}
		} else {
			// Log a warning or error if the delimiter is missing
			System.err.println("Message does not contain the expected delimiter ': ': " + message);
		}
	}

}
