package net.smartcosmos.exceptions.handlers;

import java.util.HashSet;
import java.util.IllegalFormatCodePointException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.bouncycastle.cert.ocsp.Req;
import org.junit.*;
import org.mockito.*;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import net.smartcosmos.exceptions.NoEntityFoundException;
import net.smartcosmos.exceptions.SmartCosmosException;
import net.smartcosmos.exceptions.handlers.RequestExceptionHandler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Initially created by SMART COSMOS Team on October 21, 2016.
 */
public class RequestExceptionHandlerTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    WebRequest webRequest = new DispatcherServletWebRequest(request);
    RequestExceptionHandler handler =  spy(new RequestExceptionHandler());

    @Test
    public void thatSmartCosmosExceptionWithNoCauseReturnsErrFailureResponseBody() {

        SmartCosmosException smartCosmosException = new SmartCosmosException("just message, no cause");
        ResponseEntity responseEntity = handler.handleSmartCosmosExceptionMethod(smartCosmosException, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(((Map<String, String>)responseEntity.getBody()).get("message"), "just message, no cause");
    }

    @Test
    public void thatSmartCosmosExceptionWithCauseNoEntityFoundReturnsAppropriateResponseBody() {

        NoEntityFoundException entityNotFoundException = new NoEntityFoundException("cause no entity found");
        SmartCosmosException smartCosmosException = new SmartCosmosException("caused by EntityNotFoundException", entityNotFoundException);
        ResponseEntity responseEntity = handler.handleSmartCosmosExceptionMethod(smartCosmosException, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(((Map<String, String>)responseEntity.getBody()).get("message"), "cause no entity found");
        verify(handler, times(1)).handleNoEntityFoundRequestHandlingMethod(eq(entityNotFoundException), eq(webRequest));
    }

    @Test
    @Ignore // TODO figure out how to construct a no-op ConversionFailedException
    public void thatSmartCosmosExceptionWithCauseConversionFailedReturnsAppropriateResponseBody() {
        ConversionFailedException conversionFailedException = new ConversionFailedException(TypeDescriptor.forObject(new String()), TypeDescriptor.forObject(new String()), new String("whatever"), new RuntimeException());
        SmartCosmosException smartCosmosException = new SmartCosmosException("caused by ConversionFailedException", conversionFailedException);
        ResponseEntity responseEntity = handler.handleSmartCosmosExceptionMethod(smartCosmosException, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(((Map<String, String>)responseEntity.getBody()).get("message"), "caused by ConversionFailedException");
        verify(handler, times(1)).handleConversionFailure(eq(conversionFailedException), eq(webRequest));
    }

    @Test
    public void thatSmartCosmosExceptionWithCauseIllegalArgumentReturnsAppropriateResponseBody() {

        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("illegal argument exception");
        SmartCosmosException smartCosmosException = new SmartCosmosException("caused by IllegalArgumentException", illegalArgumentException);
        ResponseEntity responseEntity = handler.handleSmartCosmosExceptionMethod(smartCosmosException, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(((Map<String, String>)responseEntity.getBody()).get("message"), "illegal argument exception");
        verify(handler, times(1)).handleIllegalArgument(eq(illegalArgumentException), eq(webRequest));
    }

    @Test
    public void thatSmartCosmosExceptionWithCauseConstraintViolationReturnsAppropriateResponseBody() {

        ConstraintViolationException constraintViolationException = new ConstraintViolationException("constraint violation exception", new HashSet<>());
        SmartCosmosException smartCosmosException = new SmartCosmosException("caused by ConstraintViolationException", constraintViolationException);
        ResponseEntity responseEntity = handler.handleSmartCosmosExceptionMethod(smartCosmosException, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(((Map<String, String>)responseEntity.getBody()).get("message"), "JSON is missing a required field or violates field constraints: ");
        verify(handler, times(1)).handleConstraintViolation(eq(constraintViolationException), eq(webRequest));
    }


}
