package com.task.emp.mng.sys.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.task.emp.mng.sys.response.ApiResponseContainer;
import com.task.emp.mng.sys.response.ResponseContainerEntity;
import com.task.emp.mng.sys.utils.Constants;

/**
 * Global exception handler that handles various exceptions throughout the
 * application. This class provides centralized exception handling and returns
 * standardized responses for client requests when errors occur.
 * 
 * <p>
 * The class includes methods to handle common exceptions such as
 * {@link DataIntegrityViolationException}, {@link NoSuchElementException},
 * {@link NoResourceFoundException},
 * {@link HttpRequestMethodNotSupportedException}, and
 * {@link HttpMessageNotReadableException}.
 * </p>
 * 
 * <p>
 * Each method returns a structured response using the
 * {@link ApiResponseContainer} to provide details about the error.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles {@link DataIntegrityViolationException}, which occurs when there is a
	 * conflict with data integrity constraints in the database.
	 *
	 * @param dataIntegrityViolationException the exception thrown when a data integrity violation occurs
	 * @return a structured response containing the error message and details
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseContainerEntity<Map<String, String>> handleInternalServerErrorException(DataIntegrityViolationException dataIntegrityViolationException) {
		Map<String, String> errors = new HashMap<>();
		String message = extractMessage(dataIntegrityViolationException.getMostSpecificCause().getMessage());
		errors.put(Constants.ERROR, message);
		return ApiResponseContainer.getResponse(Constants.INVALID_REQUEST_CONTENT, errors, HttpStatus.BAD_REQUEST, Boolean.TRUE);
	}

	/**
	 * Extracts the specific error message from the original exception message.
	 * Splits the message based on a predefined pattern for easier debugging.
	 *
	 * @param originalMessage the original exception message
	 * @return the extracted error message
	 */
	private String extractMessage(String originalMessage) {
		String[] parts = originalMessage.split(" for key ");
		if (parts.length > 0) {
			return parts[0]; // Return the part before " for key " which contains the error message
		}
		return originalMessage; // Fallback to the original message if formatting fails
	}

	/**
	 * Handles {@link NoSuchElementException}, which occurs when an expected element
	 * is not found.
	 *
	 * @param noSuchElementException the exception thrown when no element is found
	 * @return a structured response containing the error details and message
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseContainerEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		Map<String, String> errors = new HashMap<>();
		String fieldName = noSuchElementException.fillInStackTrace().getMessage();
		String message = noSuchElementException.getLocalizedMessage();
		errors.put(fieldName, message);
		return ApiResponseContainer.getResponse(Constants.NO_SUCH_ELEMENT, errors, HttpStatus.BAD_REQUEST, Boolean.TRUE);
	}

	/**
	 * Handles {@link NoResourceFoundException}, which occurs when a requested
	 * resource is not found.
	 *
	 * @param exception the exception thrown when no resource is found
	 * @return a structured response containing the error details and message
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseContainerEntity<Map<String, String>> handleNoResourceFoundException( NoResourceFoundException exception) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = exception.getMessage(); // Get the error message
		errors.put(Constants.ERROR, errorMessage);
		return ApiResponseContainer.getResponse(Constants.NO_RESOURCE_FOUND, errors, HttpStatus.NOT_FOUND, Boolean.TRUE);
	}

	/**
	 * Handles {@link HttpRequestMethodNotSupportedException}, which occurs when an
	 * unsupported HTTP method is used.
	 *
	 * @param exception the exception thrown when an unsupported method is requested
	 * @return a structured response containing the error details, attempted method, and supported methods
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseContainerEntity<Map<String, String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = exception.getMessage();
		String method = exception.getMethod();
		String supportedMethods = exception.getSupportedHttpMethods().stream().map(httpMethod -> httpMethod.name()).collect(Collectors.joining(", "));
		errors.put(Constants.ERROR, errorMessage);
		errors.put(Constants.ATTEMPTED_METHOD, method);
		errors.put(Constants.SUPPORTED_METHODS, supportedMethods);
		return ApiResponseContainer.getResponse(Constants.METHOD_NOT_ALLOWED, errors, HttpStatus.METHOD_NOT_ALLOWED, Boolean.TRUE);
	}

	/**
	 * Handles {@link HttpMessageNotReadableException}, which occurs when the
	 * request body is malformed or missing.
	 *
	 * @param exception the exception thrown when the request body cannot be read
	 * @return a structured response containing the error details and message
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseContainerEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = exception.getMessage();
		errors.put(Constants.ERROR, Constants.BAD_REQUEST);
		errors.put(Constants.MESSAGE, Constants.REQUEST_BODY_MISSING);
		errors.put(Constants.DETAILS, errorMessage); // Optionally include the original message for debugging
		return ApiResponseContainer.getResponse(Constants.BAD_REQUEST, errors, HttpStatus.BAD_REQUEST, Boolean.TRUE);
	}
}
