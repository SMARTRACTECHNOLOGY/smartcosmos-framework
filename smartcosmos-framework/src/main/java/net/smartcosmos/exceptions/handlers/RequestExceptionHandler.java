package net.smartcosmos.exceptions.handlers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.smartcosmos.exceptions.NoEntityFoundException;
import net.smartcosmos.exceptions.SmartCosmosException;

/**
 * Controller advice to translate exceptions occurring on request processing to response entities.
 */
@ControllerAdvice
@Slf4j
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_SMART_COSMOS_EXCEPTION_MESSAGE = "Unspecified SmartCosmosException";
    protected static final int ERR_FAILURE = -1;
    protected static final int ERR_FIELD_CONSTRAINT_VIOLATION = -5;
    protected static final int ERR_VALIDATION_FAILURE = -15;

    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    @ExceptionHandler(SmartCosmosException.class)
    protected ResponseEntity<?> handleSmartCosmosExceptionMethod(SmartCosmosException exception, WebRequest request) {

        if (exception.getCause() == null) {
            logException(exception, request);
            HttpHeaders headers = new HttpHeaders();
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(exception, getErrorResponseBody(ERR_FAILURE, exception.getMessage()), headers, status, request);


        }
        if (exception.getCause() instanceof NoEntityFoundException) {
            return handleNoEntityFoundRequestHandlingMethod((NoEntityFoundException)exception.getCause(), request);
        }
        if (exception.getCause() instanceof ConversionFailedException) {
            return handleConversionFailure((ConversionFailedException)exception.getCause(), request);
        }
        if (exception.getCause() instanceof IllegalArgumentException) {
            return handleIllegalArgument((IllegalArgumentException)exception.getCause(), request);
        }
        if (exception.getCause() instanceof ConstraintViolationException) {
            return handleConstraintViolation((ConstraintViolationException)exception.getCause(), request);
        }
        if (exception.getCause() instanceof MethodArgumentNotValidException) {
            HttpHeaders headers = new HttpHeaders();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleMethodArgumentNotValid((MethodArgumentNotValidException)exception.getCause(), headers, status, request);
        }
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return handleExceptionInternal(exception, getErrorResponseBody(ERR_FAILURE, exception.toString()), headers, status, request);


    }
    /**
     * <p>Customize the response for NoEntityFoundException.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. A {@code 400 Bad Request} response will be returned.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(NoEntityFoundException.class)
    protected ResponseEntity<Object> handleNoEntityFoundRequestHandlingMethod(NoEntityFoundException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logException(exception, request);

        return handleExceptionInternal(exception, getErrorResponseBody(exception.getCode(), exception.getMessage()), headers, status, request);
    }

    /**
     * <p>Customize the response for ConversionFailedException.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. If the exception was caused by an IllegalArgumentException,
     * a {@code 400 Bad Request} response will be returned, otherwise it will be a {@code 500 Internal Server Error}.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(ConversionFailedException.class)
    protected ResponseEntity<?> handleConversionFailure(ConversionFailedException exception, WebRequest request) {

        if (exception.getCause() instanceof IllegalArgumentException) {
            return handleIllegalArgument((IllegalArgumentException) exception.getCause(), request);
        }

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        logException(exception, request);

        return handleExceptionInternal(exception, getErrorResponseBody(ERR_FAILURE, exception.toString()), headers, status, request);
    }

    /**
     * <p>Customize the response for {@link IllegalArgumentException} that, e.g., is thrown when invalid URNs were submitted.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. A {@code 400 Bad Request} response will be returned.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instances
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logException(exception, request);

        return handleExceptionInternal(exception, getErrorResponseBody(ERR_VALIDATION_FAILURE, exception.getMessage()), headers, status, request);
    }

    /**
     * <p>Customize the response for ConstraintViolationException.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. A {@code 400 Bad Request} response will be returned.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logException(exception, request);

        Set<String> fieldNames = exception.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath()
                .toString())
            .collect(Collectors.toSet());

        return handleExceptionInternal(exception, processConstraintViolation(fieldNames), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {

        logException(exception, request);

        Set<String> fieldNames = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getField)
            .collect(Collectors.toSet());

        return handleExceptionInternal(exception, processConstraintViolation(fieldNames), headers, status, request);
    }

    private Map<String, Object> processConstraintViolation(Set<String> fieldNames) {

        return getErrorResponseBody(ERR_FIELD_CONSTRAINT_VIOLATION,
                                    String.format("JSON is missing a required field or violates field constraints: %s",
                                                  StringUtils.join(fieldNames, ", ")));
    }

    private Map<String, Object> getErrorResponseBody(int code, String message) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put(CODE, code);
        responseBody.put(MESSAGE, message);
        return responseBody;
    }

    private void logException(Exception exception, WebRequest request) {

        String msg = String.format("Exception on request %s: %s",
                                   request,
                                   exception.toString());
        log.warn(msg);
        log.debug(msg, exception);
    }
}
