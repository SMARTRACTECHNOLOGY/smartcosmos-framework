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

import net.smartcosmos.Field;
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

import java.text.SimpleDateFormat;
import java.util.UnknownFormatConversionException;

/**
 *
 * Encoder and decoder for metadata value objects, factored out of
 * net.smartcosmos.objects.resource.pub.MetaEncoderResource
 * to make it accessible to transactions.
 *
 */
public final class MetadataCodec
{

    public static final String RFC_3339_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

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
    public static String encodeMetadata(MetadataDataType dataType, String dataToEncode)
    {

        String resultString;
        switch (dataType)
        {
            case StringType:
            case XMLType:
                resultString = new StringMapper().toString(dataToEncode);
                break;
            case JSONType:
                JSONObject jsonObject;

                try
                {
                    jsonObject = new JSONObject(dataToEncode);
                    resultString = new JsonMapper().toString(jsonObject);
                } catch (JSONException e)
                {
                    resultString = new String();
                }
                break;
            case IntegerType:
                resultString = new IntegerMapper().toString(Integer.valueOf(dataToEncode));
                break;
            case LongType:
                resultString = new LongMapper().toString(Long.valueOf(dataToEncode));
                break;
            case FloatType:
                resultString = new FloatMapper().toString(Float.valueOf(dataToEncode));
                break;
            case DoubleType:
                resultString = new DoubleMapper().toString(Double.valueOf(dataToEncode));
                break;
            case BooleanType:
                resultString = new BooleanMapper().toString(Boolean.valueOf(dataToEncode));
                break;
            case DateType:
                try
                {
                    DateTime convertedDateTime = RFC3339.parseDateTime(dataToEncode);
                    resultString = new DateMapper().toString(convertedDateTime.toDate());
                    break;
                } catch (IllegalArgumentException invalidDateTimeException)
                {
                    throw new UnknownFormatConversionException(dataToEncode +
                            " is not formatted according to RFC 3339");
                }
            default:
                throw new IllegalArgumentException("Unsupported data type: " + dataType);
        }
        return resultString;
    }

    /**
     *
     * @param dataType an enum from net.smartcosmos.model.context.MetadataDataType
     * @param dataToDecode
     * @return JSONObject containing decoded metadata
     * @throws JSONException
     * @throws IllegalArgumentException
     */
    public static JSONObject decodeMetadata(MetadataDataType dataType, String dataToDecode)
            throws JSONException
    {
        JSONObject outputJson = new JSONObject();

        try
        {

            switch (dataType)
            {
                case StringType:
                case XMLType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new StringMapper().fromString(dataToDecode));
                    break;
                case JSONType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new JsonMapper().fromString(dataToDecode));
                    break;
                case IntegerType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new IntegerMapper().fromString(dataToDecode));
                    break;
                case LongType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new LongMapper().fromString(dataToDecode));
                    break;
                case FloatType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new FloatMapper().fromString(dataToDecode));
                    break;
                case DoubleType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new DoubleMapper().fromString(dataToDecode));
                    break;
                case BooleanType:
                    outputJson.put(Field.DECODED_VALUE_FIELD, new BooleanMapper().fromString(dataToDecode));
                    break;
                case DateType:
                    // SimpleDateFormat is NOT thread safe and must be instantiated locally like this!
                    SimpleDateFormat dateFormat = new SimpleDateFormat(RFC_3339_FORMAT);
                    outputJson.put(Field.DECODED_VALUE_FIELD, dateFormat
                            .format(new DateMapper().fromString(dataToDecode)));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported data type: " + dataType);
            }
            return outputJson;

        } catch (JSONException e)
        {
            throw e;
        }
    }
}
