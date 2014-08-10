/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerMapper implements IMetadataValueMapper<Integer>
{
    @Override
    public byte[] toBytes(Integer value)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);

        try
        {
            dataOutputStream.writeInt(value);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    @Override
    public Integer fromBytes(byte[] rawValue)
    {
        Integer intValue = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(rawValue);
        DataInputStream dataInputStream = new DataInputStream(bais);

        try
        {
            intValue = dataInputStream.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return intValue;
    }
}
