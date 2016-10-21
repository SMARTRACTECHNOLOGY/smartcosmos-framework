package net.smartcosmos.aspects;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.smartcosmos.annotation.SmartCosmosService;
import net.smartcosmos.events.SmartCosmosEventException;
import net.smartcosmos.exceptions.SmartCosmosException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = { LocalAopTestContextConfig.class })
public class SmartCosmosServiceAspectTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private SmartCosmosAopTestService testService;

    @Test
    public void capturesCheckedException() throws Exception {

        thrown.expect(SmartCosmosException.class);
        testService.testWithCheckedException();
    }

    @Test
    public void capturesRuntimeException() throws Exception {

        thrown.expect(SmartCosmosException.class);
        testService.testWithRuntimeException();
    }

    @Test
    public void capturesSmartCosmosException() throws Exception {

        thrown.expect(SmartCosmosException.class);
        testService.testWithSmartCosmosException();
    }

    @Test
    public void capturesSmartCosmosEventException() throws Exception {

        thrown.expect(SmartCosmosException.class);
        testService.testWithSmartCosmosEventException();
    }

    @Test
    public void capturesThrowable() throws Throwable {

        thrown.expect(SmartCosmosException.class);
        testService.testWithThrowable();
    }

    @Test
    public void capturesExceptionFromDefaultMethod() throws Throwable {

        thrown.expect(SmartCosmosException.class);
        testService.testDefaultMethodWithException();
    }
}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
class LocalAopTestContextConfig {

}

@SmartCosmosService
interface SmartCosmosAopTestService {

    void testWithCheckedException() throws Exception;

    void testWithRuntimeException() throws SmartCosmosException;

    void testWithSmartCosmosException() throws Exception;

    void testWithSmartCosmosEventException() throws Exception;

    void testWithThrowable() throws Throwable;

    default void testDefaultMethodWithException() throws Throwable {

        throw new Exception("This is an Exception thrown by the default interface method.");
    }

}

@Component
@SmartCosmosService
class SmartCosmosAopTestServiceDefault implements SmartCosmosAopTestService {

    public void testWithCheckedException() throws Exception {

        throw new Exception("This is an exception");
    }

    public void testWithRuntimeException() throws SmartCosmosException {

        throw new RuntimeException("This is a runtime exception");
    }

    public void testWithSmartCosmosException() throws SmartCosmosException {

        throw new SmartCosmosException("This is a SmartCosmosException.");
    }

    public void testWithSmartCosmosEventException() throws SmartCosmosException {

        Exception cause = new Exception("This is an exception");
        throw new SmartCosmosEventException("This is a SmartCosmosEventException.", cause);
    }

    public void testWithThrowable() throws Throwable {

        throw new Throwable("This is a Throwable");
    }

}
