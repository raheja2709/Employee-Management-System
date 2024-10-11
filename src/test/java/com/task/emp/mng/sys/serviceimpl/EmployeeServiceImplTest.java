package com.task.emp.mng.sys.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import com.task.emp.mng.sys.entity.Employee;
import com.task.emp.mng.sys.repository.EmployeeRepository;
import com.task.emp.mng.sys.service.impl.EmployeeServiceImpl;

/**
 * Unit tests for the {@link EmployeeServiceImpl} class. This class uses Mockito
 * to mock the dependencies of {@link EmployeeServiceImpl}, such as
 * {@link EmployeeRepository} and {@link KafkaTemplate}, and tests the core
 * functionality.
 * 
 * @author Jatin
 * @since 2024-10-11
 */
class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Set up the mocks before each test.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test for successful employee creation. Verifies the saved employee and Kafka
	 * message sending.
	 */
	@Test
	void createEmployee_Success() {
		Employee employee = new Employee(null, "John Doe", "jdoe", "IT", 60000.0);
		Employee savedEmployee = new Employee(1L, "John Doe", "jdoe", "IT", 60000.0);

		when(employeeRepository.save(employee)).thenReturn(savedEmployee);

		Employee createdEmployee = employeeService.createEmployee(employee);
		assertEquals("John Doe", createdEmployee.getName());
		assertEquals("jdoe", createdEmployee.getUsername()); // Added username verification
		assertEquals(1L, createdEmployee.getId());
		verify(kafkaTemplate).send(anyString(), anyString());
	}

	/**
	 * Test for successful employee update. Ensures the employee data is updated and
	 * the Kafka message is sent.
	 */
	@Test
	void updateEmployee_Success() {
		Employee existingEmployee = new Employee(1L, "John Doe", "jdoe", "IT", 60000.0);
		Employee updatedEmployee = new Employee(null, "Jane Doe", "jdoe2", "HR", 70000.0); // Included username

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
		when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

		Employee result = employeeService.updateEmployee(1L, updatedEmployee);
		assertEquals("Jane Doe", result.getName());
		assertEquals("jdoe2", result.getUsername()); // Added username verification
		assertEquals("HR", result.getDepartment());
		verify(kafkaTemplate).send(anyString(), anyString());
	}

	/**
	 * Test for failed employee update when the employee is not found. Ensures that
	 * a {@link NoSuchElementException} is thrown.
	 */
	@Test
	void updateEmployee_Failure_NotFound() {
		Employee updatedEmployee = new Employee(null, "Jane Doe", "jdoe2", "HR", 70000.0);
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> {
			employeeService.updateEmployee(1L, updatedEmployee);
		});
	}

	/**
	 * Test for successful employee deletion. Ensures the employee is deleted and a
	 * Kafka message is sent.
	 */
	@Test
	void deleteEmployee_Success() {
		Employee employee = new Employee(1L, "John Doe", "jdoe", "IT", 60000.0);
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));
		verify(employeeRepository).delete(employee);
		verify(kafkaTemplate).send(anyString(), anyString());
	}

	/**
	 * Test for failed employee deletion when the employee is not found. Ensures
	 * that a {@link NoSuchElementException} is thrown.
	 */
	@Test
	void deleteEmployee_Failure_NotFound() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> {
			employeeService.deleteEmployee(1L);
		});
	}

	/**
	 * Test for successfully retrieving all employees. Ensures the list of employees
	 * returned matches the expected data.
	 */
	@Test
	void getAllEmployees_Success() {
		Employee emp1 = new Employee(1L, "John Doe", "jdoe", "IT", 60000.0);
		Employee emp2 = new Employee(2L, "Jane Doe", "jdoe2", "HR", 70000.0);
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

		List<Employee> employees = employeeService.getAllEmployees();
		assertEquals(2, employees.size());
		assertEquals("John Doe", employees.get(0).getName());
		assertEquals("jdoe", employees.get(0).getUsername()); // Added username verification
		assertEquals("Jane Doe", employees.get(1).getName());
		assertEquals("jdoe2", employees.get(1).getUsername()); // Added username verification
	}

	/**
	 * Test for successfully retrieving an employee by ID. Ensures the returned
	 * employee matches the expected data.
	 */
	@Test
	void getEmployeeById_Success() {
		Employee employee = new Employee(1L, "John Doe", "jdoe", "IT", 60000.0);
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		Employee foundEmployee = employeeService.getEmployeeById(1L);
		assertEquals("John Doe", foundEmployee.getName());
		assertEquals("jdoe", foundEmployee.getUsername()); // Added username verification
	}

}
