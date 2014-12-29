package net.smartcosmos.util;

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

import com.google.common.io.BaseEncoding;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * Collection of AES-family cipher utilities.
 */
public final class CryptoUtil
{
    public static enum KeyFormat
    {
        /**
         * Public Keys.
         */
        X509,

        /**
         * Private Keys.
         */
        PKCS8
    }

    private CryptoUtil()
    {
    }

    public static String digestThenEncodePasswordForLDAP(String algorithm, String credentials)
    {
        String hashedCredentials = credentials;

        if ((credentials != null) && (credentials.length() > 0))
        {
            boolean isMD5Hash = algorithm.equalsIgnoreCase("MD5");
            boolean isSHAHash = algorithm.equalsIgnoreCase("SHA")
                    || algorithm.equalsIgnoreCase("SHA1")
                    || algorithm.equalsIgnoreCase("SHA-1");

            if (isSHAHash || isMD5Hash)
            {
                String hashAlgorithm = "MD5";
                if (isSHAHash)
                {
                    hashAlgorithm = "SHA";
                }
                try
                {
                    MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
                    md.update(credentials.getBytes("UTF-8"));
                    hashedCredentials = "{" + hashAlgorithm + "}" + BaseEncoding.base64().encode(md.digest());
                } catch (Exception e)
                {
                    hashedCredentials = null;
                }
            }
        }

        return hashedCredentials;
    }

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

    public static boolean verifySHA256withRSASignature(KeySpec publicKeySpec, byte[] utf8Bytes, String encodedSignature)
            throws CryptoException
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);

            signature.update(utf8Bytes);

            return signature.verify(BaseEncoding.base64().decode(encodedSignature));
        } catch (Exception e)
        {
            throw new CryptoException(e);
        }
    }

    public static String computeSHA256withRSASignature(KeySpec privateKeySpec, byte[] utf8Bytes) throws CryptoException
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);

            signature.update(utf8Bytes);

            byte[] signatureBytes = signature.sign();
            return BaseEncoding.base64().encode(signatureBytes);

        } catch (Exception e)
        {
            throw new CryptoException(e);
        }
    }

    public static KeySpec loadKey(InputStream inputStream, KeyFormat keyFormat) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int b;
        while ((b = inputStream.read()) != -1)
        {
            baos.write(b);
        }
        inputStream.close();

        final byte[] keyBytes = baos.toByteArray();
        baos.close();

        KeySpec keySpec;

        if (keyFormat == KeyFormat.X509)
        {
            keySpec = new X509EncodedKeySpec(keyBytes);
        } else
        {
            keySpec = new PKCS8EncodedKeySpec(keyBytes);
        }

        return keySpec;
    }

    public static KeySpec loadKey(String filePath, KeyFormat keyFormat) throws IOException
    {
        FileInputStream fis = new FileInputStream(new File(filePath));
        return loadKey(fis, keyFormat);
    }
}

