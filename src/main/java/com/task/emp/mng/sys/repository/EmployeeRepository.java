package com.task.emp.mng.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.emp.mng.sys.entity.Employee;

/**
 * Repository interface for performing CRUD operations on {@link Employee}
 * entities. This interface extends {@link JpaRepository}, providing several
 * methods for managing {@code Employee} entries in the database, such as
 * saving, deleting, updating, and finding employees.
 * 
 * <p>
 * By extending {@code JpaRepository}, this interface provides access to
 * built-in methods for employee persistence operations without requiring custom
 * SQL queries or implementation logic.
 * </p>
 * 
 * <p>
 * Common operations include finding an employee by ID, saving a new employee,
 * and deleting an existing one.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
