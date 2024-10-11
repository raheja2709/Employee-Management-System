package com.task.emp.mng.sys.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import com.task.emp.mng.sys.entity.Employee;
import com.task.emp.mng.sys.response.ResponseContainerEntity;
import com.task.emp.mng.sys.service.EmployeeService;
import com.task.emp.mng.sys.utils.Constants;

/**
 * Unit tests for the EmployeeController class, which handles HTTP requests
 * related to employee management.
 *
 * <p>
 * This class uses Mockito to mock the EmployeeService and tests various
 * scenarios for the endpoints, including successful operations and failure conditions.
 * </p>
 *
 * @author Jatin
 * @since 2024-10-11
 */
class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	private Employee employee;

	/**
	 * Sets up the test environment by initializing mocks and creating a sample Employee object before each test.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		employee = new Employee(1L, "John Doe", "johndoe", "IT", 1200.0);
	}

	/**
	 * Tests the successful creation of a new employee.
	 */
	@Test
	void createEmployee_Success() {
		when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

		ResponseContainerEntity<Employee> response = employeeController.createEmployee(employee);

		assertEquals(HttpStatus.CREATED, response.getHttpStatus());
		assertEquals(employee, response.getBody());
		assertEquals(Constants.CREATED, response.getMessage());
	}

	/**
	 * Tests the failure of creating a new employee due to a data integrity violation.
	 */
	@Test
	void createEmployee_Failure() {
		when(employeeService.createEmployee(any(Employee.class)))
				.thenThrow(new DataIntegrityViolationException("Invalid request content"));

		DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class,
				() -> employeeController.createEmployee(employee));

		assertEquals("Invalid request content", exception.getMessage());
	}

	/**
	 * Tests the successful update of an existing employee.
	 */
	@Test
	void updateEmployee_Success() {
		when(employeeService.updateEmployee(any(Long.class), any(Employee.class))).thenReturn(employee);

		ResponseContainerEntity<Employee> response = employeeController.updateEmployee(1L, employee);

		assertEquals(HttpStatus.CREATED, response.getHttpStatus());
		assertEquals(employee, response.getBody());
		assertEquals(Constants.UPDATED, response.getMessage());
	}

	/**
	 * Tests the failure of updating an employee that does not exist.
	 */
	@Test
	void updateEmployee_Failure() {
		when(employeeService.updateEmployee(any(Long.class), any(Employee.class)))
				.thenThrow(new NoSuchElementException("Object Not Found"));

		NoSuchElementException exception = assertThrows(NoSuchElementException.class,
				() -> employeeController.updateEmployee(1L, employee));

		assertEquals("Object Not Found", exception.getMessage());
	}

	/**
	 * Tests the successful deletion of an employee.
	 */
	@Test
	void deleteEmployee_Success() {
		doNothing().when(employeeService).deleteEmployee(1L);

		ResponseContainerEntity<Employee> response = employeeController.deleteEmployee(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
		assertEquals(Constants.EMPLOYEE_DELETED, response.getMessage());
	}

	/**
	 * Tests the failure of deleting an employee that does not exist.
	 */
	@Test
	void deleteEmployee_Failure() {
		doThrow(new NoSuchElementException("Employee not found")).when(employeeService).deleteEmployee(1L);

		NoSuchElementException exception = assertThrows(NoSuchElementException.class,
				() -> employeeController.deleteEmployee(1L));

		assertEquals("Employee not found", exception.getMessage());
	}

	/**
	 * Tests the successful retrieval of all employees.
	 */
	@Test
	void getAllEmployees_Success() {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeeService.getAllEmployees()).thenReturn(employees);

		ResponseContainerEntity<List<Employee>> response = employeeController.getAllEmployees();

		assertEquals(HttpStatus.OK, response.getHttpStatus());
		assertEquals(employees, response.getBody());
	}

	/**
	 * Tests the retrieval of employees when no employees are found.
	 */
	@Test
	void getAllEmployees_NoContent() {
		when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());

		ResponseContainerEntity<List<Employee>> response = employeeController.getAllEmployees();

		assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
		assertTrue(response.getBody().isEmpty());
	}

	/**
	 * Tests the successful retrieval of an employee by ID.
	 */
	@Test
	void getEmployeeById_Success() {
		when(employeeService.getEmployeeById(1L)).thenReturn(employee);

		ResponseContainerEntity<Employee> response = employeeController.getEmployeeById(1L);

		assertEquals(HttpStatus.OK, response.getHttpStatus());
		assertEquals(employee, response.getBody());
	}

	/**
	 * Tests the retrieval of an employee by ID when the employee is not found.
	 */
	@Test
	void getEmployeeById_NotFound() {
		when(employeeService.getEmployeeById(1L)).thenReturn(null);

		ResponseContainerEntity<Employee> response = employeeController.getEmployeeById(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
		assertNull(response.getBody());
	}
}
