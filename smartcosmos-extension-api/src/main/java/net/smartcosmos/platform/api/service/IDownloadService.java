package net.smartcosmos.platform.api.service;

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

import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.IRemoteContent;
import net.smartcosmos.platform.api.IService;

import java.io.IOException;

/**
 * Provides HTTP download services for remote content, eliminating an extra POST to a platform service by merely
 * pointing to a URL instead of having to actually stream over a (potentially) slow connection the actual content.
 * <p/>
 * For example, if you are on a mobile device and you want a platform service to work with a large resource found at
 * http://www.example.com/myPicture.jpg, instead of downloading the JPG to the mobile device (bandwidth used) and then
 * uploading it to the platform service (bandwidth used), simply provide the content URL and let the server download
 * it and install it. Instead of eating up bandwidth both downloading and uploading the content, only a URL needs to be
 * sent to the server!
 */
public interface IDownloadService<T extends ICosmosContext> extends IService<T>
{
    IRemoteContent getRemoteContent(String contentUrl) throws IOException;
}
