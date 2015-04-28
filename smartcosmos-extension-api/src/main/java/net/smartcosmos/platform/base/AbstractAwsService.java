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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractAwsService<U> extends AbstractService
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractAwsService.class);

    protected U credentials;

    protected AbstractAwsService(String serviceId, String name)
    {
        super(serviceId, name);
    }

    @Override
    public void initialize()
    {
        super.initialize();

        Properties p = new Properties();

        String apiKeysPath = context.getConfiguration().getServiceParameters().get("awsApiKeys");

        File apiKeysFile = new File(apiKeysPath);

        if (apiKeysFile.exists())
        {
            try
            {
                InputStream is = new FileInputStream(apiKeysFile);
                p.load(is);
                String accessKey = p.getProperty("accessKey");
                String secretAccessKey = p.getProperty("secretKey");

                credentials = createCloudCredentials(accessKey, secretAccessKey);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            LOG.error("Unable to locate the AWS API keys at path found in configuration file: {}", apiKeysPath);
        }
    }

    protected abstract U createCloudCredentials(String accessKey, String secretAccessKey);
}
