package com.task.emp.mng.sys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an Employee in the system. This class is mapped to
 * the "employees" table in the database and contains details related to an
 * employee, such as their ID, name, username, department, and salary.
 * 
 * <p>
 * Each field in this class corresponds to a column in the database table, with
 * validation constraints enforced via annotations such as {@link Column} for
 * uniqueness and non-nullability.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	/**
	 * The unique identifier for the employee. This value is automatically generated using the {@link GenerationType#IDENTITY} strategy.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The name of the employee. This field is mandatory and cannot be null.
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * The unique username of the employee. This field is mandatory and must be unique.
	 */
	@Column(nullable = false, unique = true)
	private String username;

	/**
	 * The department in which the employee works. This field is mandatory and cannot be null.
	 */
	@Column(nullable = false)
	private String department;

	/**
	 * The salary of the employee. This field is mandatory and cannot be null.
	 */
	@Column(nullable = false)
	private Double salary;

}
