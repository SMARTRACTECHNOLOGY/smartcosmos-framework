package net.smartcosmos.platform.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Base class for all AWS powered services. Instead of forming an explicit dependency on the AWS SDK, generics are used
 * for a "late" binding with the AWS SDK.
 *
 * @param <U> As this is explicitly tied to AWS access and secret keys, this is expected, but not enforced to be
 *            <code>AWSCredentials</code>.
 */
public abstract class AbstractAwsService<U> extends AbstractCloudService<U>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractAwsService.class);

    protected static final String AWS_CREDENTIALS_SERVICE_KEY = "awsApiKeys";

    protected static final String ACCESS_KEY = "accessKey";

    protected static final String SECRET_KEY = "secretKey";

    protected AbstractAwsService(String serviceId, String name)
    {
        super(AWS_CREDENTIALS_SERVICE_KEY, serviceId, name);
    }

    /**
     * Automatically extracts the {@link #ACCESS_KEY} and {@link #SECRET_KEY} from the Properties file and triggers
     * {@link #createCloudCredentials(String, String)} for final <code>AWSCredentials</code> instance creation.
     *
     * @param properties Cloud-specific credentials Property file
     * @return Expected to be an instance of <code>AWSCredentials</code>
     */
    @Override
    protected U createCloudCredentials(Properties properties)
    {
        String accessKey = properties.getProperty(ACCESS_KEY);
        String secretAccessKey = properties.getProperty(SECRET_KEY);

        return createCloudCredentials(accessKey, secretAccessKey);
    }

    /**
     * Final assembly of an <code>AWSCredentials</code> class, which is hidden behind generics in order to eliminate
     * a Cloud-specific SDK binding (AWS SDK) on the Server API framework.
     *
     * @param accessKey       AWS Access Key
     * @param secretAccessKey AWS Secret Access Key
     * @return Instance of an <code>AWSCredentials</code>
     */
    protected abstract U createCloudCredentials(String accessKey, String secretAccessKey);
}
