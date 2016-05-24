package net.smartcosmos.security.authentication.direct;

import net.smartcosmos.exceptions.NoEntityFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Customize the response for NoEntityFoundException.
     * <p>
     * This method logs a warning and delegates to {@link #handleExceptionInternal}.
     *
     * @param ex the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(NoEntityFoundException.class)
    protected ResponseEntity<Object> handleNoEntityFoundRequestHandlingMethod(
            NoEntityFoundException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logger.warn(ex.getMessage());

        return handleExceptionInternal(ex, processNoEntityFound(ex), headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationError(ConstraintViolationException ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logger.warn(ex.getMessage());

        Set<ConstraintViolation<?>> fieldErrors = ex.getConstraintViolations();
        Set<String> fieldNames = fieldErrors.stream().map(violation -> violation.getPropertyPath().toString()).collect(Collectors.toSet());

        return handleExceptionInternal(ex, processConstraintViolation(fieldNames), headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn(ex.getMessage());

        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();

        return handleExceptionInternal(ex, processFieldError(error), headers, status,
                request);
    }

    private Map<String, Object> processNoEntityFound(NoEntityFoundException ex) {
        Map<String, Object> message = new LinkedHashMap<>();

        message.put("code", ex.getCode());
        message.put("message", ex.getMessage());

        return message;
    }

    private Map<String, Object> processConstraintViolation(Set<String> fieldNames) {
        Map<String, Object> message = new LinkedHashMap<>();

        message.put("code", -5);
        message.put("message", "JSON is missing a required field or violates field constraints: " + StringUtils.join(fieldNames, ','));

        return message;
    }

    private Map<String, Object> processFieldError(FieldError error) {
        Map<String, Object> message = new LinkedHashMap<>();

        message.put("code", -5);
        message.put("message", messageSource.getMessage(error, Locale.ENGLISH));

        return message;
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
