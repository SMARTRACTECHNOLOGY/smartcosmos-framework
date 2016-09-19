package net.smartcosmos.aspects;

import org.hamcrest.Matchers;
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

import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = { LocalAopTestContextConfig.class })
public class SmartCosmosExtensionAspectsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private SmartCosmosAopTestService testService;

    @Test(expected = SmartCosmosException.class)
    public void capturesCheckedException() throws Exception {

        testService.testWithCheckedException();
    }

    @Test(expected = SmartCosmosException.class)
    public void capturesRuntimeException() throws Exception {

        testService.testWithRuntimeException();
    }

    @Test
    public void capturesSmartCosmosException() throws Exception {

        Exception expectedCause = null;
        thrown.reportMissingExceptionWithMessage("Expected '" + SmartCosmosException.class.getSimpleName() + "'!");
        thrown.expect(SmartCosmosException.class);
        thrown.expectCause(is(Matchers.nullValue()));
        testService.testWithSmartCosmosException();
    }
}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
class LocalAopTestContextConfig {}

@SmartCosmosService
interface SmartCosmosAopTestService {

    void testWithCheckedException() throws Exception;

    void testWithRuntimeException();

    void testWithSmartCosmosException() throws Exception;
}

@Component
class SmartCosmosAopTestServiceDefault implements SmartCosmosAopTestService {

    public void testWithCheckedException() throws Exception {

        throw new Exception("This is an exception");
    }

    public void testWithRuntimeException() {

        throw new RuntimeException("This is a runtime exception");
    }

    public void testWithSmartCosmosException() throws SmartCosmosException {

        throw new SmartCosmosException("This is a SmartCosmosException.");
    }
}
