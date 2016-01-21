package net.smartcosmos.platform.util;

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

public final class ClassUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(ClassUtil.class);

    private ClassUtil()
    {
    }

    public static <T> T create(Class<T> clazz, String className, ClassLoader classLoader)
    {
        T instance = null;

        try
        {
            instance = clazz.cast(Class.forName(className, true, classLoader).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            LOG.error("Unable to instantiate class named {}", className);
        }

        return instance;
    }

    public static <T> T create(Class<T> clazz, String className)
    {
        T instance = null;

        try
        {
            instance = clazz.cast(Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            LOG.error("Unable to instantiate class named {}: {}", className, e.toString());
        }

        return instance;
    }

}

