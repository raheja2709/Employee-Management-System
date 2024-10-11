package com.task.emp.mng.sys.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an audit log in the system. This class is mapped to
 * the "audit_logs" table in the database and is used to store information
 * related to changes or events that occur within the system, such as employee
 * creation, update, or deletion.
 * 
 * <p>
 * The {@code AuditLog} captures details such as event type, the name of the
 * entity affected, the entity ID, and the timestamp of when the event occurred.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

	/**
	 * The unique identifier for the audit log entry. This value is automatically generated using the {@link GenerationType#IDENTITY} strategy.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The type of event being recorded (e.g., "CREATE", "UPDATE", "DELETE"). This field represents the type of action performed on an entity.
	 */
	private String eventType;

	/**
	 * The name of the entity affected by the event (e.g., "Employee").
	 */
	private String entityName;

	/**
	 * The unique identifier of the entity affected by the event. This field records the ID of the entity being audited.
	 */
	private String entityId;

	/**
	 * The timestamp of when the event occurred. This field records the time the audit event took place, stored as a string.
	 */
	private String timestamp;
}
