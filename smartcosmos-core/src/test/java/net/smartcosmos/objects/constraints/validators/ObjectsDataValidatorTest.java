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

import java.util.Calendar;

import org.junit.*;

import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.context.Metadata;

import static org.junit.Assert.*;

/**
 * Test the ObjectsDataValidator.
 */
public class ObjectsDataValidatorTest
{
    private ObjectsDataValidator validator;

    @Before
    public void setUp() throws Exception
    {
        validator = new ObjectsDataValidator();
    }

    @Test
    public void testNull()
    {
        assertFalse("Null IMetadata should result in false validation!", validator.isValid(null, null));
    }

    @Test
    public void testValidIntValueForDateType() throws Exception
    {
        IMetadata metadata = new Metadata();
        metadata.setDataType(MetadataDataType.DateType);
        metadata.setRawValue(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        assertTrue("Metadata should be valid! data type: '" + metadata.getDataType().toString() + "', raw value: '" +
                   metadata.getRawValue() + "'.",
                   validator.isValid(metadata, null));
    }

    @Test
    public void testValidIso8601StringForDateType() throws Exception
    {
        IMetadata metadata = new Metadata();
        metadata.setDataType(MetadataDataType.DateType);
        metadata.setRawValue("2016-03-09T13:12:00+01:00");
        assertTrue("Metadata should be valid! data type: '" + metadata.getDataType().toString() + "', raw value: '" +
                   metadata.getRawValue() + "'.",
                   validator.isValid(metadata, null));
    }

    @Test
    public void testInvalidIso8601StringforDateType()
    {
        IMetadata metadata = new Metadata();
        metadata.setDataType(MetadataDataType.DateType);
        metadata.setRawValue("2016-03-09x13:12:00+01:00");
        assertFalse("Metadata should be invalid! data type: '" + metadata.getDataType().toString() + "', raw value: '" +
                   metadata.getRawValue() + "'.",
                   validator.isValid(metadata, null));
    }
}