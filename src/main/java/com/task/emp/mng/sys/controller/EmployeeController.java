package com.task.emp.mng.sys.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.emp.mng.sys.entity.Employee;
import com.task.emp.mng.sys.response.ApiResponseContainer;
import com.task.emp.mng.sys.response.ResponseContainerEntity;
import com.task.emp.mng.sys.service.EmployeeService;
import com.task.emp.mng.sys.utils.ApiPathConstants;
import com.task.emp.mng.sys.utils.Constants;

import jakarta.validation.Valid;

/**
 * Controller class for managing Employee-related operations. This class
 * provides endpoints for creating, updating, deleting, and retrieving
 * employees. It maps requests to the "/employees" API path, following RESTful
 * conventions.
 *
 * @author Jatin
 * @since 2024-10-11
 */
@RestController
@RequestMapping(ApiPathConstants.EMPLOYEES)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Creates a new employee.
	 *
	 * @param employee the employee object to be created, validated before processing
	 * @return a response containing the newly created employee and a success message
	 */
	@PostMapping
	public ResponseContainerEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		return ApiResponseContainer.getResponse(Constants.CREATED, employeeService.createEmployee(employee), HttpStatus.CREATED);
	}

	/**
	 * Updates an existing employee based on the given ID.
	 *
	 * @param id the ID of the employee to be updated
	 * @param employee the updated employee data, validated before processing
	 * @return a response containing the updated employee and a success message, or a not found response if the employee doesn't exist
	 */
	@PutMapping(ApiPathConstants.ID)
	public ResponseContainerEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
		return ApiResponseContainer.getResponse(Constants.UPDATED, employeeService.updateEmployee(id, employee), HttpStatus.CREATED);
	}

	/**
	 * Deletes an employee based on the given ID.
	 *
	 * @param id the ID of the employee to be deleted
	 * @return a response indicating the success of the deletion, or a not found response if the employee doesn't exist
	 */
	@DeleteMapping(ApiPathConstants.ID)
	public ResponseContainerEntity<Employee> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ApiResponseContainer.getResponse(Constants.EMPLOYEE_DELETED, HttpStatus.NO_CONTENT);
	}

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return a response containing a list of employees, or a no content response if no employees are found
	 */
	@GetMapping
	public ResponseContainerEntity<List<Employee>> getAllEmployees() {
		List<Employee> listOfEmployees = employeeService.getAllEmployees();
		return ApiResponseContainer.getResponse("Success", listOfEmployees, listOfEmployees.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	/**
	 * Retrieves a specific employee by ID.
	 *
	 * @param id the ID of the employee to retrieve
	 * @return a response containing the employee data, or a no content response if the employee is not found
	 */
	@GetMapping(ApiPathConstants.ID)
	public ResponseContainerEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ApiResponseContainer.getResponse("Success", employee, Objects.nonNull(employee) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
