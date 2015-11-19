package net.smartcosmos.pojo.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Core
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


import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by asiegel on 19.11.2015.
 */
public interface IResult
{
    int getCode();
    String getFormattedMessage();

    static IResult translate(int code) throws IllegalArgumentException
    {
        return ResultTranslator.getInstance().translate(code);
    }

    final class ResultTranslator
    {
        private static ResultTranslator instance;
        private Set<Class<? extends IResult>> resultClasses;

        private ResultTranslator()
        {
            Reflections reflections = new Reflections("net.smartcosmos");
            resultClasses = reflections.getSubTypesOf(IResult.class);
        }

        public static ResultTranslator getInstance()
        {
            if (instance == null)
            {
                instance = new ResultTranslator();
            }

            return instance;
        }

        public IResult translate(int code) throws IllegalArgumentException
        {
            List<IResult> resultList = new ArrayList<>();

            for (Class<? extends IResult> clazz : resultClasses)
            {
                try
                {
                    Method method = clazz.getMethod("translate", int.class);
                    Object result = method.invoke(null, code);
                    if (result instanceof IResult)
                    {
                        resultList.add((IResult) result);
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
            }

            int resultSize = resultList.size();
            if (resultSize != 1)
            {
                if (resultSize > 1)
                {
                    throw new IllegalArgumentException("Duplicate Result: " + code);
                } else
                {
                    throw new IllegalArgumentException("Unknown Result: " + code);
                }
            } else
            {
                return resultList.get(0);
            }
        }
    }
}
