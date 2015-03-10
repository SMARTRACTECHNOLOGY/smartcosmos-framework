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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashingInputStream extends FilterInputStream
{
    private final MessageDigest digest;

    public HashingInputStream(InputStream inputStream, String hashAlgorithm) throws NoSuchAlgorithmException
    {
        super(inputStream);
        digest = MessageDigest.getInstance(hashAlgorithm);
    }

    public synchronized int read() throws IOException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        int bytesRead = super.read(b);
        if (bytesRead > 0)
        {
            digest.update(b, 0, bytesRead);
        }

        return bytesRead;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException
    {
        int bytesRead = super.read(b, off, len);
        if (bytesRead > 0)
        {
            digest.update(b, off, bytesRead);
        }
        return bytesRead;
    }

    @Override
    public void close() throws IOException
    {
        super.close();
    }

    public byte[] getSignature() throws IOException
    {
        return digest.digest();
    }
}
