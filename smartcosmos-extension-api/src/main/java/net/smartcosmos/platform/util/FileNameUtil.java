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

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.objects.model.context.IFile;

public final class FileNameUtil
{
    private FileNameUtil()
    {
    }

    public static String generateFileName(IUser authenticatedUser, IFile file, String mimeType)
    {
        Preconditions.checkNotNull(mimeType, "The mimeType must not be null");

        String ext;

        String mediaType = mimeType.toLowerCase();

        if (mediaType.equals("image/svg"))
        {
            ext = ".svg";
        } else if (mediaType.equals("audio/mpeg"))
        {
            ext = ".mp3";
        } else if (mediaType.equals("image/tiff"))
        {
            ext = ".tiff";
        } else if (mediaType.equals("text/properties"))
        {
            ext = ".properties";
        } else if (mediaType.equals("video/mp4"))
        {
            ext = ".mp4";
        } else if (mediaType.equals("text/plain"))
        {
            ext = ".txt";
        } else if (mediaType.equals("image/png"))
        {
            ext = ".png";
        } else if (mediaType.equals("image/jpg") || mediaType.equals("image/jpeg"))
        {
            ext = ".jpg";
        } else if (mediaType.equals("text/xml"))
        {
            ext = ".xml";
        } else if (mediaType.equals("application/json"))
        {
            ext = ".json";
        } else
        {
            ext = mediaType.replace('/', '-');
        }

        StringBuilder fileNameBuilder = new StringBuilder();

        fileNameBuilder.append("account_").append(authenticatedUser.getAccount().getUrn().replace(':', '-'))
                .append("/")
                .append("user_")
                .append(authenticatedUser.getUrn().replace(':', '-'))
                .append("/")
                .append(file.getEntityReferenceType().name())
                .append("/")
                .append(file.getReferenceUrn().replace(':', '-'))
                .append("/")
                .append("file_")
                .append(file.getUrn().replace(':', '-'))
                .append("__")
                .append(System.currentTimeMillis())
                .append(ext);

        return fileNameBuilder.toString();
    }
}
