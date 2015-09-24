package net.smartcosmos.platform.test;

import org.junit.runner.JUnitCore;

public final class TestCommandLineRunner
{

    /**
     * Utility class.
     */
    private TestCommandLineRunner()
    {
    }

    /**
     * 
     * This is a convenience class, since you cannot include default program arguments in the manifest file. If there
     * are no program arguments then it'll pass in the test class. We just call JUnit with the default test class,
     * otherwise it'll use the passed in arguments.
     * 
     * @param args
     *            arguments passed to application.
     */
    public static void main(String[] args)
    {
        if (args != null && args.length > 0)
        {
            JUnitCore.main(args);
        } else
        {
            JUnitCore.main(PopulateObjectsServer.class.getCanonicalName());
        }
    }

}
