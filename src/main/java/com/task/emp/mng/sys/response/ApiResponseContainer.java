package com.task.emp.mng.sys.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * A utility class for creating {@link ResponseContainerEntity} instances.
 * <p>
 * This class provides static methods to create response containers with various
 * combinations of response body, HTTP status, message, and error indicators.
 * </p>
 * 
 * @author Jatin
 * @since 2024-10-11
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseContainer {

	/**
	 * Create a response container with the specified body.
	 *
	 * @param <T>  The type of the response body.
	 * @param body The response body.
	 * @return A response container with the provided body.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(T body) {
		return new ResponseContainerEntity<>(body);
	}

	/**
	 * Create a response container with the specified HTTP status.
	 *
	 * @param <T>        The type of the response body.
	 * @param httpStatus The HTTP status.
	 * @return A response container with the provided HTTP status.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(httpStatus);
	}

	/**
	 * Create a response container with the specified message and body.
	 *
	 * @param <T>     The type of the response body.
	 * @param message The response message.
	 * @param body    The response body.
	 * @return A response container with the provided message and body.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(String message, T body) {
		return new ResponseContainerEntity<>(message, body);
	}

	/**
	 * Create a response container with the specified message and httpStatus.
	 *
	 * @param <T>        The type of the response body.
	 * @param message    The response message.
	 * @param httpStatus The response httpStatus.
	 * @return A response container with the provided message and httpStatus.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(String message, HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(message, httpStatus);
	}

	/**
	 * Create a response container with the specified message, body and httpStatus.
	 *
	 * @param <T>        The type of the response body.
	 * @param message    The response message.
	 * @param body       The response body.
	 * @param httpStatus The response httpStatus.
	 * @return A response container with the provided message, body and httpStatus.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(message, body, httpStatus);
	}

	/**
	 * Create a response container with the specified message, body and isError.
	 *
	 * @param <T>     The type of the response body.
	 * @param message The response message.
	 * @param body    The response body.
	 * @param isError The response isError.
	 * @return A response container with the provided message, body and isError.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, boolean isError) {
		return new ResponseContainerEntity<>(message, body, isError);
	}

	/**
	 * Create a response container with the specified message, body, httpStatus and
	 * isError.
	 *
	 * @param <T>        The type of the response body.
	 * @param message    The response message.
	 * @param body       The response body.
	 * @param httpStatus The response httpStatus.
	 * @param isError    The response isError.
	 * @return A response container with the provided message, body, httpStatus and
	 *         isError.
	 */
	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, HttpStatus httpStatus,
			boolean isError) {
		return new ResponseContainerEntity<>(message, body, httpStatus, isError);
	}

}
