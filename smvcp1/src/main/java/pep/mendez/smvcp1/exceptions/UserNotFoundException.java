package pep.mendez.smvcp1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pep
 * 
 *         Referenced by UserRestController
 * 
 *         Marks a method or exception class with the status code() and reason()
 *         that should be returned.
 * 
 *         The status code is applied to the HTTP response when the handler
 *         method is invoked and overrides status information set by other
 *         means, like ResponseEntity or "redirect:".
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Usuario no existe")
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
