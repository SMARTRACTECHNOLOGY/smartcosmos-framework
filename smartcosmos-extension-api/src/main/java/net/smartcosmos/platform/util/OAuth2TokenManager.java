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

import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.oauth.IOAuthToken;
import net.smartcosmos.platform.pojo.oauth.OAuthToken;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class OAuth2TokenManager
{
    private volatile SecureRandom random;

    private volatile int count = 0;

    private static final int MAX_TOKENS_BETWEEN_RESEED = 1000;

    private static final OAuth2TokenManager INSTANCE = new OAuth2TokenManager();

    private OAuth2TokenManager()
    {
        try
        {
            random = SecureRandom.getInstance("SHA1PRNG");

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    public static OAuth2TokenManager getInstance()
    {
        return INSTANCE;
    }

    public String generateCsrfToken()
    {
        return generate(20);
    }

    public String generateAuthorizationCode()
    {
        return generate(20);
    }

    public IOAuthToken generateToken(IContext context)
    {
        return new OAuthToken("bearer",
                generate(40),
                context.getConfiguration().getOAuth2Factory().getMaxBearerTokenLifeMillis() / 1000,
                generate(40));
    }

    private String generate(int len)
    {
        if (count++ > MAX_TOKENS_BETWEEN_RESEED)
        {
            count = 0;
            random.setSeed(random.generateSeed(20));
        }

        byte[] token = new byte[len];
        random.nextBytes(token);
        return toHex(token);
    }

    private String toHex(byte[] input)
    {
        StringBuilder sb = new StringBuilder();
        for (byte anInput : input)
        {
            String d = Integer.toHexString(Byte.valueOf(anInput).intValue() & 0xFF);
            if (d.length() == 1)
            {
                sb.append('0');
            }
            sb.append(d);
        }
        return sb.toString();
    }
}
