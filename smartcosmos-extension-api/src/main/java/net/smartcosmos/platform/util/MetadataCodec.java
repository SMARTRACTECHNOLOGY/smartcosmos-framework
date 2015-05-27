/*
 * Copyright (C> 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */

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

import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.util.mapper.BooleanMapper;
import net.smartcosmos.util.mapper.DateMapper;
import net.smartcosmos.util.mapper.DoubleMapper;
import net.smartcosmos.util.mapper.FloatMapper;
import net.smartcosmos.util.mapper.IntegerMapper;
import net.smartcosmos.util.mapper.JsonMapper;
import net.smartcosmos.util.mapper.LongMapper;
import net.smartcosmos.util.mapper.StringMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UnknownFormatConversionException;

/**
 *
 * Encoder for metadata value objects, factored out of
 * net.smartcosmos.objects.resource.pub.MetaEncoderResource
 * to make it accessible to transactions. The actual decoding part of
 * net.smartcosmos.objects.resource.pub.MetaEncoderResource may also eventually land here.
 *
 */
public final class MetadataCodec
{

    protected static final DateTimeFormatter RFC3339 = ISODateTimeFormat.dateTimeParser();

    // checkstyle objects to public or default constructors in utility classes
    private MetadataCodec()
    {

    }

    /**
     *
     * @param dataType an enum from net.smartcosmos.model.context.MetadataDataType
     * @param dataToEncode
     * @return byte[] of encoded data
     */
    public static byte [] encodeMetadata(MetadataDataType dataType, String dataToEncode)
    {

        byte[] bytes;
        switch (dataType)
        {
            case StringType:
            case XMLType:
                bytes = new StringMapper().toBytes(dataToEncode);
                break;
            case JSONType:
                JSONObject jsonObject;

                try
                {
                    jsonObject = new JSONObject(dataToEncode);
                    bytes = new JsonMapper().toBytes(jsonObject);
                } catch (JSONException e)
                {
                    bytes = new byte[]{};
                }
                break;
            case IntegerType:
                bytes = new IntegerMapper().toBytes(Integer.valueOf(dataToEncode));
                break;
            case LongType:
                bytes = new LongMapper().toBytes(Long.valueOf(dataToEncode));
                break;
            case FloatType:
                bytes = new FloatMapper().toBytes(Float.valueOf(dataToEncode));
                break;
            case DoubleType:
                bytes = new DoubleMapper().toBytes(Double.valueOf(dataToEncode));
                break;
            case BooleanType:
                bytes = new BooleanMapper().toBytes(Boolean.valueOf(dataToEncode));
                break;
            case DateType:
                try
                {
                    DateTime convertedDateTime = RFC3339.parseDateTime(dataToEncode);
                    bytes = new DateMapper().toBytes(convertedDateTime.toDate());
                    break;
                } catch (IllegalArgumentException invalidDateTimeException)
                {
                    throw new UnknownFormatConversionException(dataToEncode +
                            " is not formatted according to RFC 3339");
                }
            default:
                throw new IllegalArgumentException("Unsupported data type: " + dataType);
        }
        return bytes;
    }
}
