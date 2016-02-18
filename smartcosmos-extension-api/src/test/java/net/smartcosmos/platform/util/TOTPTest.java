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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.pojo.context.User;
import org.apache.commons.codec.binary.Base32;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class TOTPTest
{
    public static final String SECRET = "OVEK7TIJ3A3DM3M6";

    @Test
    public void testSecretGeneration() throws IOException
    {
        String secret = Rfc6238.generate();
        assertNotNull(secret);
    }

    @Test
    public void testGenerateTOTPUrl() throws Exception
    {
        IAccount account = new Account();
        account.setName("smartcosmos");
        IUser user = new User();
        user.setAccount(account);
        user.setGivenName("Jason");

        Rfc6238.getQRBarcodeURLQuery(user, SECRET);
    }

    @Test
    public void testGenerateQRCodeUrl() throws Exception
    {
        IAccount account = new Account();
        account.setName("smartcosmos");
        IUser user = new User();
        user.setAccount(account);
        user.setGivenName("Jason");

        String url = Rfc6238.generateQRBarcodeURL(user, SECRET);
        System.out.println(url);
    }

    @Test
    public void confirmValidCode() throws Exception
    {
        int code = Rfc6238.getCodeForTimeIndex(new Base32().decode(SECRET), Rfc6238.getCurrentTimeIndex());
        System.out.println("Code: " + code);

        boolean validCode = Rfc6238.verifyCodeForCurrentTimeIndex(SECRET, code, 2);
        System.out.println("Valid: " + validCode);

        System.out.println("codes: " + getCodeList(SECRET, Rfc6238.getCurrentTimeIndex(), 2));

        assertTrue(validCode);
    }

    @Test
    public void confirmInvalidCode() throws Exception
    {
        int code = 999999;
        boolean validCode = Rfc6238.verifyCodeForCurrentTimeIndex(SECRET, code, 2);
        assertTrue(!validCode);
    }

    static long getCode(String secret, long timeIndex)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        return Rfc6238.getCodeForTimeIndex(new Base32().decode(secret), timeIndex);
    }

    static List getCodeList(String secret, long timeIndex, int variance) throws NoSuchAlgorithmException, InvalidKeyException
    {
        List list = new ArrayList();
        for (int i = -variance; i <= variance; i++)
        {
            list.add(getCode(SECRET, timeIndex + i));
        }
        return list;
    }


}
