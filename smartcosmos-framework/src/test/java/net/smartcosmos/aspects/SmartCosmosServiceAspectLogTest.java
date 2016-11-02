package net.smartcosmos.aspects;

import java.util.List;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

import net.smartcosmos.exceptions.SmartCosmosException;

import static ch.qos.logback.classic.Level.DEBUG;
import static ch.qos.logback.classic.Level.TRACE;
import static ch.qos.logback.classic.Level.WARN;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class SmartCosmosServiceAspectLogTest {

    @Mock
    JoinPoint joinPoint;

    @Mock
    Signature signature;

    @Mock
    Throwable throwable;

    @Spy
    SmartCosmosServiceAspect aspect = new SmartCosmosServiceAspect();

    @Mock
    Appender mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    private static final Object TARGET = "target";
    private static final String SIGNATURE_STRING = "signature";

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SmartCosmosServiceAspect.class);

    @Before
    public void setUp() {

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        when(mockAppender.getName()).thenReturn("MOCK");
        root.addAppender(mockAppender);
        root.setLevel(TRACE);

        when(joinPoint.getTarget()).thenReturn(TARGET);
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.toLongString()).thenReturn(SIGNATURE_STRING);
    }

    @After
    public void tearDown() {

        reset(aspect, mockAppender, throwable, joinPoint, signature);
    }

    @Test
    public void thatMockingWorks() {

        assertNotNull(aspect);
        assertNotNull(mockAppender);
        assertNotNull(joinPoint);
        assertNotNull(signature);
        assertNotNull(throwable);
        assertNotNull(logger);
    }

    @Test
    public void thatSmartCosmosExceptionDoesNotLog() {

        Throwable throwable = new SmartCosmosException("some message");

        try {
            aspect.handleServiceExceptions(joinPoint, throwable);
        } catch (SmartCosmosException e) {
            // expected, but need try-catch for further verifications below
        }

        verifyZeroInteractions(mockAppender);
    }

    @Test
    public void thatExceptionLogsAtWarnLevel() {

        Throwable throwable = new Exception("some message");

        try {
            aspect.handleServiceExceptions(joinPoint, throwable);
        } catch (SmartCosmosException e) {
            // expected, but need try-catch for further verifications below
        }

        verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
        List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();

        Boolean loggedAtWarn = false;
        for (LoggingEvent loggingEvent : loggingEvents) {
            if (WARN.equals(loggingEvent.getLevel())) {
                loggedAtWarn = true;
                break;
            }
        }
        assertTrue(loggedAtWarn);
    }

    @Test
    public void thatExceptionLogsAtDebugLevel() {

        Throwable throwable = new Exception("some message");

        try {
            aspect.handleServiceExceptions(joinPoint, throwable);
        } catch (SmartCosmosException e) {
            // expected, but need try-catch for further verifications below
        }

        verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
        List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();

        Boolean loggedAtDebug = false;
        for (LoggingEvent loggingEvent : loggingEvents) {
            if (DEBUG.equals(loggingEvent.getLevel())) {
                loggedAtDebug = true;
                break;
            }
        }
        assertTrue(loggedAtDebug);
    }

    @Test
    public void thatExceptionLogsWithToString() {

        Throwable throwable = new Exception("some message");

        try {
            aspect.handleServiceExceptions(joinPoint, throwable);
        } catch (SmartCosmosException e) {
            // expected, but need try-catch for further verifications below
        }

        verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent = captorLoggingEvent.getAllValues()
            .get(0);
        String logMessage = loggingEvent.getFormattedMessage();

        assertTrue(logMessage.contains(throwable.toString()));
    }

    @Test
    public void thatHttpStatusCodeExceptionLogsWithResponseBody() {

        String responseBody = "some response body";
        Throwable throwable = mock(HttpStatusCodeException.class);
        String expectedLogText = String.format("HTTP response body: '%s'", responseBody);

        when(((HttpStatusCodeException) throwable).getResponseBodyAsString()).thenReturn(responseBody);

        try {
            aspect.handleServiceExceptions(joinPoint, throwable);
        } catch (SmartCosmosException e) {
            // expected, but need try-catch for further verifications below
        }

        verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent = captorLoggingEvent.getAllValues()
            .get(0);
        String logMessage = loggingEvent.getFormattedMessage();

        assertTrue(logMessage.contains(expectedLogText));
    }
}
