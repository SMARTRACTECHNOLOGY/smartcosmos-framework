package net.smartcosmos.util;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.objects.model.context.IFile;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Collection of SHA-family utilities.
 */
public final class HashUtil
{
    public static final int PASSWORD_LENGTH = 12;

    public static final char[] SPECIAL_CHARACTERS = new char[]{'!', '@', '#', '$', '%', '&', '*', '+', '?'};

    private static final int HASH_ITERATIONS = 2048;

    private HashUtil()
    {
    }

    public static JSONObject signFile(IFile file, byte[] signature) throws JSONException
    {
        JSONObject body = new JSONObject()
                .put("accountUrn", file.getAccount().getUrn())
                .put("fileUrn", file.getUrn())
                .put("entityReferenceType", file.getEntityReferenceType())
                .put("referenceUrn", file.getReferenceUrn())
                .put("url", file.getUrl())
                .put("contentsSignature", HexUtil.bytesToHex(signature));

        String hash = new Sha256Hash(body.toString(3)).toHex();

        return new JSONObject()
                .put("signedBody", body)
                .put("signature", hash)
                .put("algorithm", "SHA-256")
                .put("iterations", 1)
                .put("library", "Apache Shiro (Java)")
                .put("description", "The contentsSignature is of the file contents exclusively. " +
                        "The root signature is of the signedBody exclusively");
    }

    public static String createHash(char[] chars, InputStream saltStream)
    {
        return (new Sha256Hash(chars, saltStream, HASH_ITERATIONS).toBase64());
    }

    public static String createRandomToken(int length)
    {
        long key = -1;

        StringBuilder builder = new StringBuilder(length);

        try
        {
            SecureRandom csprng = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < length; i++)
            {
                key = (csprng.nextInt(26) + 65);
                builder.append((char) key);
            }
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static String createRandomPassword()
    {
        long key = -1;
        String now = Long.toString(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder(PASSWORD_LENGTH);

        builder.append(now.toCharArray()[0]);

        try
        {
            SecureRandom csprng = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < (PASSWORD_LENGTH - 5); i++)
            {
                key = (csprng.nextInt(26) + 65);
                builder.append((char) key);
            }

            // at least one lower case letter
            key = (csprng.nextInt(26) + 97);
            builder.append((char) key);

            // at least one number
            key = (csprng.nextInt(10) + 48);
            builder.append((char) key);

            builder.append(SPECIAL_CHARACTERS[csprng.nextInt(SPECIAL_CHARACTERS.length)]);

            builder.append(now.toCharArray()[now.length() - 1]);

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
