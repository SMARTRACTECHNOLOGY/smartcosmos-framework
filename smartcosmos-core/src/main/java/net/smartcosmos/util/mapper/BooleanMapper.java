package net.smartcosmos.util.mapper;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import static com.google.common.base.Preconditions.checkArgument;

public class BooleanMapper implements IMetadataValueMapper<Boolean>
{
    @Override
    public byte[] toBytes(Boolean value)
    {
        if (value)
        {
            return new byte[]{0x01};
        } else
        {
            return new byte[]{0x00};
        }
    }

    @Override
    public Boolean fromBytes(byte[] rawValue)
    {
        checkArgument(((null != rawValue) && rawValue.length > 0), "rawValue must be non-null with a length of 1");
        return (rawValue[0] == 0x01);
    }
}
