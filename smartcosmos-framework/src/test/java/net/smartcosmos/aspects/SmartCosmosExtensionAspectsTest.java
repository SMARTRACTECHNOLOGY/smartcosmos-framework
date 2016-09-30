package net.smartcosmos.aspects;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.smartcosmos.annotation.SmartCosmosService;
import net.smartcosmos.exceptions.SmartCosmosException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = { LocalAopTestContextConfig.class })
public class SmartCosmosExtensionAspectsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private SmartCosmosAopTestService testService;

    @Test
    public void capturesCheckedException() throws Exception {

        thrown.reportMissingExceptionWithMessage("Expected '" + SmartCosmosException.class.getSimpleName() + "'!");
        thrown.expect(SmartCosmosException.class);
        testService.testWithCheckedException();
    }

    @Test
    public void capturesRuntimeException() throws Exception {

        thrown.reportMissingExceptionWithMessage("Expected '" + SmartCosmosException.class.getSimpleName() + "'!");
        thrown.expect(SmartCosmosException.class);
        testService.testWithRuntimeException();
    }

    @Test
    public void capturesSmartCosmosException() throws Exception {

        thrown.reportMissingExceptionWithMessage("Expected '" + SmartCosmosException.class.getSimpleName() + "'!");
        thrown.expect(SmartCosmosException.class);
        testService.testWithSmartCosmosException();
    }

    @Test
    public void capturesThrowable() throws Throwable {

        thrown.reportMissingExceptionWithMessage("Expected '" + SmartCosmosException.class.getSimpleName() + "'!");
        thrown.expect(SmartCosmosException.class);
        testService.testWithThrowable();
    }
}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
class LocalAopTestContextConfig {}


interface SmartCosmosAopTestService {

    void testWithCheckedException() throws Exception;

    void testWithRuntimeException() throws SmartCosmosException;

    void testWithSmartCosmosException() throws Exception;

    void testWithThrowable() throws Throwable;
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

    public void testWithThrowable() throws Throwable {

        throw new Throwable("This is a Throwable");
    }

}
