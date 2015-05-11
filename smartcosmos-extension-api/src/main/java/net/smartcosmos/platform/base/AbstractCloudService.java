package net.smartcosmos.platform.base;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Base class for any Cloud-backed service offering that provides a simple Properties file based mechanism for loading
 * Cloud-specific credentials and creating a Cloud-specific credentials class.
 * <p/>
 * For example, the AWS SDK relies on an interface named <code>AWSCredentials</code> that consists of an access key
 * and a secret key. Instead of creating an explicit dependency on the AWS SDK in the Server API, and a dependency
 * for every other Cloud provider that might be eventually suppported, this base class abstracts away the actual
 * credentialing routines.
 *
 * @param <U> Cloud specific credentialing class, e.g. <code>AWSCredentials</code> in the case of AWS
 */
public abstract class AbstractCloudService<U> extends AbstractService
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCloudService.class);

    protected U credentials;

    protected String serviceKey;

    protected AbstractCloudService(String serviceKey, String serviceId, String name)
    {
        super(serviceId, name);

        Preconditions.checkNotNull(serviceKey, "serviceKey must not be null");
        this.serviceKey = serviceKey;
    }

    /**
     * Automatically attempts to load a standard Properties file pointed to by the {@link #serviceKey} constructor
     * parameter. If the {@link #serviceKey} doesn't exist, then {@link #onMissingServiceKey()} event is triggered. If
     * the path pointed to by the {@link #serviceKey} doesn't exist, then {@link #onMissingFileAtServiceKeyPath(String)}
     * event is triggered. If the Properties file is successfully loaded, then it is passed into the credentialing
     * routine, {@link #createCloudCredentials(Properties)}.
     */
    @Override
    public void initialize()
    {
        super.initialize();

        Properties properties = new Properties();

        if (context.getConfiguration().getServiceParameters().containsKey(serviceKey))
        {
            String cloudCredentialsPath = context.getConfiguration().getServiceParameters().get(serviceKey);

            File cloudCredentialsFile = new File(cloudCredentialsPath);

            if (cloudCredentialsFile.exists())
            {
                try
                {
                    InputStream is = new FileInputStream(cloudCredentialsFile);
                    properties.load(is);

                    credentials = createCloudCredentials(properties);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else
            {
                onMissingFileAtServiceKeyPath(cloudCredentialsPath);
            }
        } else
        {
            onMissingServiceKey();
        }
    }

    /**
     * Concrete classes use this routine to create Cloud-specific credentials.
     *
     * @param properties Cloud-specific credentials Property file
     * @return Instance of a Cloud-specific credentials class
     */
    protected abstract U createCloudCredentials(Properties properties);

    /**
     * Default event merely logs the fact that the {@link #serviceKey} doesn't exist in the configuration.
     */
    protected void onMissingServiceKey()
    {
        LOG.error("Unable to locate a Cloud service key with name: {}", serviceKey);
    }

    /**
     * Default event merely logs the fact that the path pointed to by the {@link #serviceKey} doesn't exist on the
     * file system.
     */
    protected void onMissingFileAtServiceKeyPath(String cloudCredentialsPath)
    {
        LOG.error("Unable to locate Cloud credential properties at path found in configuration file: {}", cloudCredentialsPath);
    }
}
