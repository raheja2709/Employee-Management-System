package com.task.emp.mng.sys.service;

import java.util.List;

import com.task.emp.mng.sys.entity.Employee;

/**
 * This interface provides the contract for employee-related services. It
 * includes methods for creating, updating, deleting, and retrieving employee
 * information.
 * 
 * @author Jatin
 * @since 2024-10-11
 */
public interface EmployeeService {

	/**
	 * Creates a new employee with the provided details.
	 *
	 * @param employee the employee object containing the details of the employee to be created
	 * @return the created employee object
	 */
	public Employee createEmployee(Employee employee);

	/**
	 * Updates the details of an existing employee identified by the given ID.
	 *
	 * @param id  the ID of the employee to be updated
	 * @param updatedEmployee the employee object containing the updated details
	 * @return the updated employee object
	 */
	public Employee updateEmployee(Long id, Employee updatedEmployee);

	/**
	 * Deletes an employee identified by the given ID.
	 *
	 * @param id the ID of the employee to be deleted
	 */
	public void deleteEmployee(Long id);

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return a list of employee objects
	 */
	public List<Employee> getAllEmployees();

	/**
	 * Retrieves an employee identified by the given ID.
	 *
	 * @param id the ID of the employee to be retrieved
	 * @return the employee object if found, or null if not found
	 */
	public Employee getEmployeeById(Long id);

}
