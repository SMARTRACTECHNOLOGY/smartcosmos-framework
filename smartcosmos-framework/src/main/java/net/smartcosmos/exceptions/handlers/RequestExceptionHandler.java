package net.smartcosmos.exceptions.handlers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.smartcosmos.exceptions.NoEntityFoundException;

@ControllerAdvice
@Slf4j
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final int ERR_FAILURE = -1;
    private static final int ERR_FIELD_CONSTRAINT_VIOLATION = -5;
    private static final int ERR_VALIDATION_FAILURE = -15;

    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    /**
     * <p>Customize the response for NoEntityFoundException.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(NoEntityFoundException.class)
    protected ResponseEntity<Object> handleNoEntityFoundRequestHandlingMethod(NoEntityFoundException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        log.warn(exception.getMessage());

        return handleExceptionInternal(exception, processNoEntityFound(exception), headers, status, request);
    }

    /**
     * Customize the response for ConstraintViolationException.
     * <p>
     * This method logs a warning and delegates to {@link #handleExceptionInternal}.
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationError(ConstraintViolationException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        log.warn(exception.getMessage());

        Set<ConstraintViolation<?>> fieldErrors = exception.getConstraintViolations();
        Set<String> fieldNames = fieldErrors.stream()
            .map(violation -> violation.getPropertyPath()
                .toString())
            .collect(Collectors.toSet());

        return handleExceptionInternal(exception, processConstraintViolation(fieldNames), headers, status, request);
    }

    @ExceptionHandler(ConversionFailedException.class)
    protected ResponseEntity<?> handleConversionFailedException(ConversionFailedException exception, WebRequest request) {

        if (exception.getCause() instanceof IllegalArgumentException) {
            return handleIllegalArgumentException((IllegalArgumentException) exception.getCause(), request);
        }

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> message = new LinkedHashMap<>();
        message.put(CODE, ERR_FAILURE);
        message.put(MESSAGE, exception.toString());

        log.warn(exception.toString());

        return handleExceptionInternal(exception, message, headers, status, request);
    }

    /**
     * <p>Customize the response for {@link IllegalArgumentException} that, e.g., is thrown when invalid URNs were submitted.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instances
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> message = new LinkedHashMap<>();
        message.put(CODE, ERR_VALIDATION_FAILURE);
        message.put(MESSAGE, exception.getMessage());

        logger.warn(exception.getMessage());

        return handleExceptionInternal(exception, message, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {

        logger.warn(exception.getMessage());

        BindingResult result = exception.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        Set<String> fieldNames = errors.stream()
            .map(FieldError::getField)
            .collect(Collectors.toSet());

        return handleExceptionInternal(exception, processConstraintViolation(fieldNames), headers, status, request);
    }

    private Map<String, Object> processNoEntityFound(NoEntityFoundException exception) {

        Map<String, Object> message = new LinkedHashMap<>();

        message.put(CODE, exception.getCode());
        message.put(MESSAGE, exception.getMessage());

        return message;
    }

    private Map<String, Object> processConstraintViolation(Set<String> fieldNames) {

        Map<String, Object> message = new LinkedHashMap<>();

        message.put(CODE, ERR_FIELD_CONSTRAINT_VIOLATION);
        message.put(MESSAGE, "JSON is missing a required field or violates field constraints: " + StringUtils.join(fieldNames, ", "));

        return message;
    }
}
