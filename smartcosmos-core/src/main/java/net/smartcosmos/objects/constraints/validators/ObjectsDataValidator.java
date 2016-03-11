package net.smartcosmos.objects.constraints.validators;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Core
 * ===============================================================================
 * Copyright (C) 2013 - 2016 Smartrac Technology Fletcher, Inc.
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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.objects.constraints.ObjectsMetadata;

/**
 * Checks that a given character sequence is a valid objects data format.
 * <p>
 * Currently only the Date validation is supported.
 * </p>
 */
public class ObjectsDataValidator implements ConstraintValidator<ObjectsMetadata, IMetadata>
{
    private static Logger logger = LoggerFactory.getLogger(ObjectsDataValidator.class);

    @Override
    public void initialize(ObjectsMetadata constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(IMetadata value, ConstraintValidatorContext context)
    {
        if (value == null)
        {
            return false;
        }

        switch (value.getDataType())
        {
            case DateType:
                return isDateValid(value);
            default:
        }

        return true;
    }

    private boolean isDateValid(IMetadata metadata)
    {
        if (StringUtils.isNumeric(metadata.getRawValue()))
        {
            return ObjectsNumericDateValidator.isValid(metadata.getRawValue());
        }

        return ObjectsStringDateValidator.isValid(metadata.getRawValue());
    }

    private static class ObjectsNumericDateValidator
    {
        public static boolean isValid(String numericDateAsString)
        {
            try
            {
                Long parsedValue = Long.valueOf(numericDateAsString);
                return true;
            } catch (NumberFormatException e)
            {
                logger.info("Invalid Date format for metadata '{}', cause: '{}'", numericDateAsString, e.toString());
            }
            return false;
        }
    }

    private static class ObjectsStringDateValidator
    {
        public static boolean isValid(String iso8601DateString)
        {
            try
            {
                new DateTime(iso8601DateString);
                return true;
            } catch (IllegalArgumentException e)
            {
                logger.info("Invalid Date format for metadata '{}', cause: '{}'", iso8601DateString, e.toString());
            }
            return false;
        }
    }
}
