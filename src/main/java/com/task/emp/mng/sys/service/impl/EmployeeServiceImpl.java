package com.task.emp.mng.sys.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.task.emp.mng.sys.entity.Employee;
import com.task.emp.mng.sys.repository.EmployeeRepository;
import com.task.emp.mng.sys.service.EmployeeService;
import com.task.emp.mng.sys.utils.Constants;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = Constants.EMPLOYEE_EVENTS;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee createEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		kafkaTemplate.send(TOPIC, "CREATE: " + savedEmployee.getId());
		return savedEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee existingEmployee = optionalEmployee.get();
		if (updatedEmployee.getName() != null && !updatedEmployee.getName().trim().isEmpty()) {
			existingEmployee.setName(updatedEmployee.getName());
		}

		// Update the department only if it's not null or blank
		if (updatedEmployee.getDepartment() != null && !updatedEmployee.getDepartment().trim().isEmpty()) {
			existingEmployee.setDepartment(updatedEmployee.getDepartment());
		}

		// Update the salary only if it's not null
		if (updatedEmployee.getSalary() != null) {
			existingEmployee.setSalary(updatedEmployee.getSalary());
		}

		// Update the username only if it's not null or blank
		if (updatedEmployee.getUsername() != null && !updatedEmployee.getUsername().trim().isEmpty()) {
			existingEmployee.setUsername(updatedEmployee.getUsername());
		}

		Employee savedEmployee = employeeRepository.save(existingEmployee);
		kafkaTemplate.send(TOPIC, "UPDATE: " + savedEmployee.getId());
		return savedEmployee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		employeeRepository.delete(optionalEmployee.get());
		kafkaTemplate.send(TOPIC, "DELETE: " + id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployeeById(Long id) {
		kafkaTemplate.send(TOPIC, "READ: " + id);
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee.isPresent() ? employee.get() : null;
	}

}
