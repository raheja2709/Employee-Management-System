package com.task.emp.mng.sys.utils;

/**
 * This class holds constant values that are used throughout the application.
 * These constants typically represent error messages, log messages, or
 * event-related strings. Using these constants helps in managing and
 * standardizing messages across the system.
 * 
 * <p>
 * Constants include common response messages for error handling, resource
 * operations, and HTTP-related status descriptions.
 * </p>
 * 
 * <p>
 * This class is intended to provide easy access to reusable string values to
 * avoid duplication and ensure consistency.
 * </p>
 * 
 * <p>
 * These constants are public, static, and final, meaning they are immutable and
 * can be accessed without an instance of the class.
 * </p>
 * 
 * <p>
 * Example usage: {@code Constants.ERROR}
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
public class Constants {

	/**
	 * General error message key.
	 */
	public static final String ERROR = "error";

	/**
	 * Key for general message information.
	 */
	public static final String MESSAGE = "Message";

	/**
	 * Key for additional details in responses or logs.
	 */
	public static final String DETAILS = "Details";

	/**
	 * Key for logging or tracking attempted method names.
	 */
	public static final String ATTEMPTED_METHOD = "attempted_method";

	/**
	 * Key representing the methods supported for a given resource.
	 */
	public static final String SUPPORTED_METHODS = "Supported_Methods";

	/**
	 * Message indicating that the request content was invalid.
	 */
	public static final String INVALID_REQUEST_CONTENT = "Invalid request content";

	/**
	 * Message indicating that no object or resource was found.
	 */
	public static final String NO_SUCH_ELEMENT = "Object Not Found";

	/**
	 * Message indicating that no specific resource was found.
	 */
	public static final String NO_RESOURCE_FOUND = "Resource Not Found";

	/**
	 * Message indicating that the HTTP method used is not allowed for this
	 * endpoint.
	 */
	public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";

	/**
	 * Message indicating that the required request body is missing or malformed.
	 */
	public static final String REQUEST_BODY_MISSING = "Required request body is missing or malformed.";

	/**
	 * Message indicating that an employee was successfully created.
	 */
	public static final String CREATED = "Employee Successfully Created";

	/**
	 * Message indicating that an employee was successfully updated.
	 */
	public static final String UPDATED = "Employee Successfully Updated";

	/**
	 * Message indicating that an employee was successfully deleted.
	 */
	public static final String EMPLOYEE_DELETED = "Employee Deleted Successfully";

	/**
	 * Message indicating that a resource was not found.
	 */
	public static final String NOT_FOUND = "Not Found";

	/**
	 * Message indicating a conflict in the request (e.g., duplicate resources).
	 */
	public static final String CONFLICT = "Conflict";

	/**
	 * Message indicating that the request was malformed or invalid.
	 */
	public static final String BAD_REQUEST = "Bad Request";

	/**
	 * Name of the event related to employee-related actions or changes.
	 */
	public static final String EMPLOYEE_EVENTS = "employee_events";

}
