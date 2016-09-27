package net.smartcosmos.security.authentication.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @Autowired
    MessageSource messageSource;

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

        logger.warn(ex.getMessage());

        return handleExceptionInternal(ex, null, headers, status, request);
    }
}
