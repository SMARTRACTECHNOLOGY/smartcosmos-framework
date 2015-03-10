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

package net.smartcosmos.platform.util;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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

import com.google.common.io.BaseEncoding;
import net.smartcosmos.model.context.IUser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Rfc6238
{
    private static final String HMAC_SHA_1 = "HmacSHA1";

    private Rfc6238()
    {
    }

    /**
     * @return 10 byte Base32 (RFC3548) encoded random secret
     */
    public static String generate()
    {
        byte[] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        return BaseEncoding.base32().encode(buffer);
    }

    public static String generateQRBarcodeURL(IUser user, String secret) throws UnsupportedEncodingException
    {
        return "https://chart.googleapis.com/chart?" + getQRBarcodeURLQuery(user, secret);
    }

    public static String getQRBarcodeURLQuery(IUser user, String secret) throws UnsupportedEncodingException
    {
        return "chs=200x200&chld=M%7C0&cht=qr&chl=" + URLEncoder.encode(getQRBarcodeOtpAuthURL(user, secret), "ASCII");
    }

    public static String getQRBarcodeOtpAuthURL(IUser user, String secret)
    {
        return String.format("otpauth://totp/%s@%s&secret=%s",
                user.getGivenName(),
                user.getAccount().getName(),
                secret);
    }

    public static long getCurrentTimeIndex()
    {
        return System.currentTimeMillis() / 1000 / 30;
    }

    public static int getCodeForTimeIndex(byte[] secret, long timeIndex)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(timeIndex);
        byte[] timeBytes = buffer.array();

        SecretKeySpec signKey = new SecretKeySpec(secret, HMAC_SHA_1);

        Mac mac = Mac.getInstance(HMAC_SHA_1);
        mac.init(signKey);

        byte[] hash = mac.doFinal(timeBytes);
        int offset = hash[19] & 0xf;
        long truncatedHash = hash[offset] & 0x7f;
        for (int i = 1; i < 4; i++)
        {
            truncatedHash <<= 8;
            truncatedHash |= hash[offset + i] & 0xff;
        }
        truncatedHash %= 1000000;

        return (int) truncatedHash;
    }

    public static boolean verifyCodeForCurrentTimeIndex(String secret, int code, int variance) throws Exception
    {
        long timeIndex = getCurrentTimeIndex();

        byte[] secretBytes = BaseEncoding.base32().decode(secret);
        for (int i = -variance; i <= variance; i++)
        {
            if (getCodeForTimeIndex(secretBytes, timeIndex + i) == code)
            {
                return true;
            }
        }
        return false;
    }
}
