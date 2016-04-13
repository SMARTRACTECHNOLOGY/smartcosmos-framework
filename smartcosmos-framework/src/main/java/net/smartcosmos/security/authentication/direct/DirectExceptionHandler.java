package net.smartcosmos.security.authentication.direct;

import net.smartcosmos.exceptions.NoEntityFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * Controller advice to translate the server side exceptions the media type that the
 * request came in as.
 *
 * @author voor
 * @see ResponseEntityExceptionHandler
 * @see #handleException(Exception, WebRequest)
 * @see org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver
 */
@ControllerAdvice
public class DirectExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Customize the response for AccessDeniedException.
	 * <p>
	 * This method logs a warning and delegates to {@link #handleExceptionInternal}.
	 *
	 * @param ex the exception
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDeniedRequestHandlingMethod(
			AccessDeniedException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.FORBIDDEN;

		pageNotFoundLogger.warn(ex.getMessage());

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@ExceptionHandler(NoEntityFoundException.class)
	protected ResponseEntity<Object> handleNoEntityFoundRequestHandlingMethod(
			NoEntityFoundException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.BAD_REQUEST;

		pageNotFoundLogger.warn(ex.getMessage());

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex,
					WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<Object>(body, headers, status);
	}

}
