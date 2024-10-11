package com.task.emp.mng.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.emp.mng.sys.entity.AuditLog;

/**
 * Repository interface for performing CRUD operations on {@link AuditLog}
 * entities. This interface extends {@link JpaRepository}, providing methods to
 * manage {@code AuditLog} entries in the database, such as saving, deleting,
 * and finding audit logs.
 * 
 * <p>
 * By extending {@code JpaRepository}, it inherits several methods to operate on
 * {@code AuditLog} without the need for boilerplate code, such as finding by
 * ID, saving an audit log, deleting, and more.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
