/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * Collection of AES-family cipher utilities.
 */
public class CryptoUtil
{
    /**
     * @param ivBytes        Must be exactly 16 bytes. Can be openly shared.
     * @param keyBytes       Should be "UTF-8" bytes.
     * @param clearTextBytes Clear text to encrypt
     * @return Cipher text
     * @throws java.io.UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] ivBytes, byte[] keyBytes, byte[] clearTextBytes)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(clearTextBytes);
    }

    /**
     * @param ivBytes         Must be exactly 16 bytes. Can be openly shared.
     * @param keyBytes        Should be "UTF-8" bytes.
     * @param cipherTextBytes Cipher text to decrypt
     * @return Clear text
     * @throws java.io.UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(byte[] ivBytes, byte[] keyBytes, byte[] cipherTextBytes)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(cipherTextBytes);
    }

    public static Cipher getCipher(int mode, byte[] ivBytes, byte[] keyBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
    {
        Cipher cipher = null;

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, newKey, ivSpec);

        return cipher;
    }

    public static byte[] getKeyBytes(InputStream saltStream, String userName, String password)
            throws UnsupportedEncodingException
    {
        //
        //
        // NOTE: This approach used appears in the next 6 lines
        // appears to be FAR faster than using the PKCS#5 code
        // in PBEKeySpec on the Android platform
        //
        //
        char[] keyMaterial = (userName + password).toCharArray();
        String b64KeyMaterial = HashUtil.createHash(keyMaterial, saltStream);
        return Arrays.copyOf(b64KeyMaterial.getBytes("UTF-8"), 32);
    }

    public static byte[] getInitVector() throws UnsupportedEncodingException
    {
        return "*GdY1@|f36,!fKL#".getBytes("UTF-8");
    }
}

