package com.task.emp.mng.sys.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * A container class for encapsulating responses in the API.
 * <p>
 * This class holds the response body, HTTP status code, error indication, and a
 * message. It is used to standardize the format of responses returned by the
 * API.
 * </p>
 * 
 * @param <T> The type of the response body.
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@Getter
@Setter
public class ResponseContainerEntity<T> {

	/**
	 * The response body.
	 */
	private T body;

	/**
	 * The HTTP status code.
	 */
	private HttpStatus httpStatus;

	/**
	 * Indicates whether the response represents an error.
	 */
	private boolean isError;

	/**
	 * A message associated with the response.
	 */
	private String message;

	/**
	 * Constructs a ResponseContainer with a given response body.
	 *
	 * @param body The response body.
	 */
	public ResponseContainerEntity(T body) {
		this.body = body;
	}

	/**
	 * Constructs a ResponseContainer with the specified HTTP status.
	 *
	 * @param httpStatus The HTTP status code.
	 */
	public ResponseContainerEntity(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

	/**
	 * Constructs a ResponseContainer with the specified message and response body.
	 *
	 * @param message The message associated with the response.
	 * @param body    The response body.
	 */
	public ResponseContainerEntity(String message, T body) {
		this.message = message;
		this.body = body;
	}

	/**
	 * Constructs a ResponseContainer with the specified message, response body, and
	 * HTTP status.
	 *
	 * @param message    The message associated with the response.
	 * @param body       The response body.
	 * @param httpStatus The HTTP status code.
	 */
	public ResponseContainerEntity(String message, T body, HttpStatus httpStatus) {
		this.message = message;
		this.body = body;
		this.httpStatus = httpStatus;
	}

	/**
	 * Constructs a ResponseContainer with the specified HTTP status and message.
	 *
	 * @param message    The message associated with the response.
	 * @param httpStatus The HTTP status code.
	 */
	public ResponseContainerEntity(String message, HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	/**
	 * Constructs a ResponseContainer with the specified message, response body, and
	 * error indicator.
	 *
	 * @param message The message associated with the response.
	 * @param body    The response body.
	 * @param isError Indicates whether the response represents an error.
	 */
	public ResponseContainerEntity(String message, T body, boolean isError) {
		this.message = message;
		this.body = body;
		this.isError = isError;
	}

	/**
	 * Constructs a ResponseContainer with the specified message, response body,
	 * HTTP status, and error indicator.
	 *
	 * @param message    The message associated with the response.
	 * @param body       The response body.
	 * @param httpStatus The HTTP status code.
	 * @param isError    Indicates whether the response represents an error.
	 */
	public ResponseContainerEntity(String message, T body, HttpStatus httpStatus, boolean isError) {
		this.message = message;
		this.body = body;
		this.httpStatus = httpStatus;
		this.isError = isError;
	}

}